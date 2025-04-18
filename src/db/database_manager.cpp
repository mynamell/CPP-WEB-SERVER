#include "database_manager.hpp"
#include "../utils/logger.hpp"
#include <sstream>

DatabaseManager::DatabaseManager(const std::string& db_path) : db_path_(db_path), db_(nullptr) {
    {
        std::lock_guard<std::recursive_mutex> lock(mutex_);
        int rc = sqlite3_open(db_path.c_str(), &db_);//打开数据库
        if (rc) {
            LOG_ERROR << "Can't open database: " << sqlite3_errmsg(db_);
            return;
        }
        LOG_INFO << "Opened database successfully";
    }
    initializeTables();
}

DatabaseManager::~DatabaseManager() {
    std::lock_guard<std::recursive_mutex> lock(mutex_);//可重入的互斥锁不会产生死锁
    if (db_) {
        sqlite3_close(db_);
    }
}

bool DatabaseManager::initializeTables() {
    // Users table
    const char* create_users_table = 
        "CREATE TABLE IF NOT EXISTS users ("
        "username TEXT PRIMARY KEY,"
        "password_hash TEXT NOT NULL,"
        "created_at INTEGER NOT NULL,"
        "is_online INTEGER DEFAULT 0,"//在线
        "last_active_time INTEGER DEFAULT 0);";//最后在线时间

    // Rooms table
    const char* create_rooms_table = 
        "CREATE TABLE IF NOT EXISTS rooms ("
        "name TEXT PRIMARY KEY,"
        "creator TEXT NOT NULL,"
        "created_at INTEGER NOT NULL,"
        "FOREIGN KEY(creator) REFERENCES users(username));";

    // Room members table
    const char* create_room_members_table = 
        "CREATE TABLE IF NOT EXISTS room_members ("//聊天室成员信息
        "room_name TEXT NOT NULL,"
        "username TEXT NOT NULL,"
        "joined_at INTEGER NOT NULL,"//加入时间
        "PRIMARY KEY(room_name, username),"
        "FOREIGN KEY(room_name) REFERENCES rooms(name),"
        "FOREIGN KEY(username) REFERENCES users(username));";

    // Messages table
    const char* create_messages_table = 
        "CREATE TABLE IF NOT EXISTS messages ("//存储消息
        "id INTEGER PRIMARY KEY AUTOINCREMENT,"
        "room_name TEXT NOT NULL,"
        "username TEXT NOT NULL,"
        "content TEXT NOT NULL,"
        "timestamp INTEGER NOT NULL,"
        "FOREIGN KEY(room_name) REFERENCES rooms(name),"
        "FOREIGN KEY(username) REFERENCES users(username));";
//执行sql查询
    return executeQuery(create_users_table) &&
           executeQuery(create_rooms_table) &&
           executeQuery(create_room_members_table) &&
           executeQuery(create_messages_table);
}

bool DatabaseManager::executeQuery(const std::string& query) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    char* err_msg = nullptr;
    //                     库链接对象,执行sql语句，回调函数，回调用户数据,错误消息
    int rc = sqlite3_exec(db_, query.c_str(), nullptr, nullptr, &err_msg);
    
    if (rc != SQLITE_OK) {
        LOG_ERROR << "SQL error: " << err_msg;
        sqlite3_free(err_msg);
        return false;
    }
    return true;
}

bool DatabaseManager::createUser(const std::string& username, const std::string& password_hash) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::stringstream ss;//创建一个可读写的字符串流
    ss << "INSERT INTO users (username, password_hash, created_at) VALUES ('"
       << username << "', '" << password_hash << "', "
       << std::chrono::system_clock::now().time_since_epoch().count() << ");";
    return executeQuery(ss.str());
}
//有效用户
bool DatabaseManager::validateUser(const std::string& username, const std::string& password_hash) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::stringstream ss;
    ss << "SELECT COUNT(*) FROM users WHERE username = '" << username 
       << "' AND password_hash = '" << password_hash << "';";
    
    sqlite3_stmt* stmt;
    //ss.str里面包含sql语句,c_str() 将其转换为 C 风格的字符串。这是要执行的 SQL 查询
    if (sqlite3_prepare_v2(db_, ss.str().c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        return false;
    }
    
    bool valid = false;
    if (sqlite3_step(stmt) == SQLITE_ROW) {
        valid = (sqlite3_column_int(stmt, 0) > 0);
    }
    
    sqlite3_finalize(stmt);
    return valid;
}
//检查用户是否存在
bool DatabaseManager::userExists(const std::string& username) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::stringstream ss;
    ss << "SELECT COUNT(*) FROM users WHERE username = '" << username << "';";
    
    sqlite3_stmt* stmt;
    if (sqlite3_prepare_v2(db_, ss.str().c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        return false;
    }
    
    bool exists = false;//用户是否存在的标志位
    if (sqlite3_step(stmt) == SQLITE_ROW) {
        exists = (sqlite3_column_int(stmt, 0) > 0);
    }
    
    sqlite3_finalize(stmt);//重置状态
    return exists;
}

bool DatabaseManager::createRoom(const std::string& name, const std::string& creator) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::stringstream ss;
    ss << "INSERT INTO rooms (name, creator, created_at) VALUES ('"
       << name << "', '" << creator << "', "
       << std::chrono::system_clock::now().time_since_epoch().count() << ");";
    return executeQuery(ss.str());
}

bool DatabaseManager::deleteRoom(const std::string& name) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::stringstream ss;
    ss << "DELETE FROM rooms WHERE name = '" << name << "';";
    return executeQuery(ss.str());
}

std::vector<std::string> DatabaseManager::getRooms() {//获取聊天室列表
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::vector<std::string> rooms;
    const char* query = "SELECT name FROM rooms;";
    
    sqlite3_stmt* stmt;
    if (sqlite3_prepare_v2(db_, query, -1, &stmt, nullptr) != SQLITE_OK) {
        return rooms;
    }
    
    while (sqlite3_step(stmt) == SQLITE_ROW) {//逐步处理查询结果
        rooms.push_back(reinterpret_cast<const char*>(sqlite3_column_text(stmt, 0)));
    }
    
    sqlite3_finalize(stmt);
    return rooms;
}

bool DatabaseManager::addRoomMember(const std::string& room_name, const std::string& username) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    
    // 首先检查用户是否已经在房间中
    std::stringstream check_ss;
    check_ss << "SELECT COUNT(*) FROM room_members WHERE room_name = '" 
            << room_name << "' AND username = '" << username << "';";
    
    sqlite3_stmt* stmt;
    if (sqlite3_prepare_v2(db_, check_ss.str().c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        return false;
    }
    
    bool exists = false;
    if (sqlite3_step(stmt) == SQLITE_ROW) {
        exists = (sqlite3_column_int(stmt, 0) > 0);
    }
    
    sqlite3_finalize(stmt);
    
    // 如果用户已经在房间中，直接返回true
    if (exists) {
        return true;
    }
    
    // 用户不在房间中，执行插入操作
    std::stringstream insert_ss;
    insert_ss << "INSERT INTO room_members (room_name, username, joined_at) VALUES ('"
             << room_name << "', '" << username << "', "
             << std::chrono::system_clock::now().time_since_epoch().count() << ");";
    return executeQuery(insert_ss.str());
}

bool DatabaseManager::removeRoomMember(const std::string& room_name, const std::string& username) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::stringstream ss;
    ss << "DELETE FROM room_members WHERE room_name = '" << room_name 
       << "' AND username = '" << username << "';";
    return executeQuery(ss.str());
}

std::vector<std::string> DatabaseManager::getRoomMembers(const std::string& room_name) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::vector<std::string> members;
    std::stringstream ss;
    ss << "SELECT username FROM room_members WHERE room_name = '" << room_name << "';";
    
    sqlite3_stmt* stmt;
    if (sqlite3_prepare_v2(db_, ss.str().c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        return members;
    }
    
    while (sqlite3_step(stmt) == SQLITE_ROW) {//sqlite3_column_text(stmt, 0)查询某一行的某一列的文本值
        members.push_back(reinterpret_cast<const char*>(sqlite3_column_text(stmt, 0)));//添加查询结果
    }
    
    sqlite3_finalize(stmt);
    return members;
}

bool DatabaseManager::saveMessage(const std::string& room_name, const std::string& username, 
                                const std::string& content, int64_t timestamp) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::stringstream ss;
    ss << "INSERT INTO messages (room_name, username, content, timestamp) VALUES ('"
       << room_name << "', '" << username << "', '" << content << "', " << timestamp << ");";
    return executeQuery(ss.str());
}
//按时间戳获取指定聊天室的内容
std::vector<nlohmann::json> DatabaseManager::getMessages(const std::string& room_name, int64_t since) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::vector<nlohmann::json> messages;
    std::stringstream ss;
    ss << "SELECT username, content, timestamp FROM messages WHERE room_name = '" 
       << room_name << "' ORDER BY timestamp ASC;";
    
    sqlite3_stmt* stmt;
    if (sqlite3_prepare_v2(db_, ss.str().c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        return messages;
    }
    
    while (sqlite3_step(stmt) == SQLITE_ROW) {
        nlohmann::json msg;
        msg["username"] = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 0));
        msg["content"] = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 1));
        msg["timestamp"] = sqlite3_column_int64(stmt, 2);
        messages.push_back(msg);
    }
    
    sqlite3_finalize(stmt);
    return messages;
}
//设置用户在线状态
bool DatabaseManager::setUserOnlineStatus(const std::string& username, bool is_online) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::stringstream ss;
    ss << "UPDATE users SET is_online = " << (is_online ? 1 : 0);
    if (is_online) {
        ss << ", last_active_time = " << std::chrono::system_clock::now().time_since_epoch().count();
    }
    ss << " WHERE username = '" << username << "';";
    return executeQuery(ss.str());
}

bool DatabaseManager::updateUserLastActiveTime(const std::string& username) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::stringstream ss;
    ss << "UPDATE users SET last_active_time = " //更新为当前时间
       << std::chrono::system_clock::now().time_since_epoch().count()
       << " WHERE username = '" << username << "';";
    return executeQuery(ss.str());
}
//检查更新长时间未活动的用户状态
bool DatabaseManager::checkAndUpdateInactiveUsers(int64_t timeout_ms) {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    int64_t current_time = std::chrono::system_clock::now().time_since_epoch().count();
    int64_t timeout_time = current_time - timeout_ms;//计算是否超时来判断是否改变状态
    
    std::stringstream ss;
    ss << "UPDATE users SET is_online = 0 WHERE is_online = 1 AND "
       << "last_active_time < " << timeout_time << ";";
    return executeQuery(ss.str());
}

std::vector<User> DatabaseManager::getAllUsers() {
    std::lock_guard<std::recursive_mutex> lock(mutex_);
    std::vector<User> users;
    const char* query = "SELECT username, password_hash, is_online FROM users;";
    sqlite3_stmt* stmt;
    
    if (sqlite3_prepare_v2(db_, query, -1, &stmt, nullptr) != SQLITE_OK) {
        LOG_ERROR << "Failed to prepare statement: " << sqlite3_errmsg(db_);
        return users;
    }
    //while读取每一行数据
    while (sqlite3_step(stmt) == SQLITE_ROW) {
        const char* username = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 0));
        const char* password = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 1));
        bool is_online = sqlite3_column_int(stmt, 2) > 0;
        users.push_back({std::string(username), std::string(password), is_online});
    }
    
    sqlite3_finalize(stmt);
    return users;
}
