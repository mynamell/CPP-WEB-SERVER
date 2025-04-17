#include "logger.hpp"
#include <iostream>
#include <fstream>
#include <sstream>
#include <chrono>
#include <filesystem>
#include <algorithm>
#include <queue>
#include <mutex>
#include <condition_variable>
#include <thread>


namespace utils {
    //定义颜色结构体
    struct Color{
        static constexpr const char* RESET   = "\033[0m";  // 重置颜色
        static constexpr const char* RED     = "\033[31m"; // 红色（错误）
        static constexpr const char* GREEN   = "\033[32m"; // 绿色（信息）
        static constexpr const char* YELLOW  = "\033[33m"; // 黄色（警告）
        static constexpr const char* BLUE    = "\033[34m"; // 蓝色
        static constexpr const char* MAGENTA = "\033[35m"; // 洋红色
        static constexpr const char* CYAN    = "\033[36m"; // 青色
        static constexpr const char* BOLD    = "\033[1m";  // 加粗

    }    
    //获取文件名
    static const char* getFileName(const char* filePath) {
        const char* fileName = filePath;
        for(const char* p=filePath;*p;++p){
            if(*p=='/' || *p=='\\'){
                fileName = p + 1;
            }
        }
        return fileName;
    }
    //更据日志级别获取对应字符串表示
    static const char* getLevelStr(LogLevel level) {
        switch (level) {
            case LogLevel::DEBUG: return "DEBUG";
            case LogLevel::INFO:  return "INFO";
            case LogLevel::WARN:  return "WARN";
            case LogLevel::ERROR: return "ERROR";
            case LogLevel::FATAL: return "FATAL";
            default:              return "UNKNOWN";
        }
    }
    // 根据日志级别获取对应的颜色
static const char* getLevelColor(LogLevel level) {
    switch (level) {
        case LogLevel::DEBUG: return Color::RESET;
        case LogLevel::INFO:  return Color::GREEN;
        case LogLevel::WARN:  return Color::YELLOW;
        case LogLevel::ERROR: return Color::RED;
        case LogLevel::FATAL: return Color::RED;
        default:              return Color::RESET;
    }
}
//日志批量处理的阈值
static constexpr size_t BATCH_PROCESSING_THRESHOLD = 100;
//初始化全局日志级别
LogLevel Logger::LogLevel global_log_level = LogLevel::DEBUG;
//初始化日志系统
void Logger::init(const LogConfig& config) {
    getInstance().initLogger(config);
}
// 获取 Logger 的单例实例
Logger& Logger::getInstance() {
    static Logger instance;
    return instance;
}
// 记录日志
void Logger::log(LogLevel level, const char* file, const char* function, int line, const std::string& message) {
    LogStream(level, file, function, line) << message;
}
// 设置全局日志级别
void Logger::setGlobalLevel(LogLevel level) {
    LogLevel global_log_level = level;
}
// 获取全局日志级别
LogLevel Logger::getGlobalLevel() {
    return global_log_level;
}
// 获取线程本地的字符串流（避免频繁构造和销毁）
static std::ostringstream& getStream() {
    static thread_local std::ostringstream stream; // 使用线程局部存储，确保每个线程有独立的字符串流
    stream.str("");  // 清空流内容，避免残留数据
    stream.clear();  // 清空流的错误状态，确保可以重新使用
    return stream;   // 返回线程本地的字符串流
}
//获取当前时间的字符串表示
static inline char* printCurrentTime() {
    auto now = std::chrono::system_clock::now(); // 获取当前系统时间
    std::time_t now_time = std::chrono::system_clock::to_time_t(now); // 转换为 time_t 类型
    std::tm now_tm = {}; // 用于存储本地时间的结构体
    #ifdef _WIN32
    if (localtime_s(&now_tm, &now_time) == 0) { // Windows 平台下的线程安全本地时间转换
#else
    if (localtime_r(&now_time, &now_tm)) { // 非 Windows 平台下的线程安全本地时间转换
#endif
        auto now_ms = std::chrono::time_point_cast<std::chrono::milliseconds>(now).time_since_epoch().count(); // 获取当前时间的毫秒数
        constexpr int buffer_size = 32; // 定义缓冲区大小
        static thread_local char buffer[buffer_size]; // 使用线程局部存储的缓冲区，避免多线程冲突
        auto write = std::snprintf(buffer, sizeof(buffer), "%04d-%02d-%02d %02d:%02d:%02d.%03d",
            now_tm.tm_year + 1900, now_tm.tm_mon + 1, now_tm.tm_mday, // 年、月、日
            now_tm.tm_hour, now_tm.tm_min, now_tm.tm_sec, // 时、分、秒
            (int)(now_ms % 1000)); // 毫秒部分
        buffer[std::min(write, buffer_size - 1)] = 0; // 确保缓冲区以空字符结尾
        return buffer; // 返回格式化后的时间字符串
    } else {
        return (char*)""; // 如果时间转换失败，返回空字符串
    }
}

//LogStream 构造函数
Logger::LogStream::LogStream(LogLevel level,const char* file,const char* function,int line)
    :level_(level),
    stream_(getStream()) // 获取线程本地的字符串流
{
    if(level_>=Logger::getGlobalLevel()) { // 仅记录高于全局日志级别的日志
        stream_ << getLevelColor(level_) // 添加日志级别对应的颜色
        << '[' << printCurrentTime() << "] " // 添加当前时间
        << '[' << getLevelStr(level_) << "] " // 添加日志级别字符串
        << "[" << std::this_thread::get_id() << "] " // 添加线程 ID
        << '[' << getFileName(file) << ':' << function << ":" << line  << "] "; // 添加文件名、函数名和行号

    }

}

//LogStream析构函数
Logger::LogStream::~LogStream() {
    if(level_>=Logger::getGlobalLevel()) { // 仅记录高于全局日志级别的日志
        stream_ << Color::RESET; // 重置颜色
        Logger::getInstance().writeToFile(stream_.str()); // 将日志写入文件
    }
}

// 初始化日志系统
void Logger::initLogger(){
    config_ = config; // 设置日志配置
    current_file_path_ = getNewLogFilePath(); // 获取新的日志文件路径
    openLogFile(); // 打开日志文件
    if(config_.async_mode) { // 如果启用异步模式
        write_thread_ = std::thread(&Logger::processLogQueue, this); // 启动日志处理线程
    }
}
//打开日志文件
void Logger::openLogFile(){
    if(log_file_.is_open()) {
        log_file_.close(); // 如果文件已经打开，先关闭
    }
    log_file_.open(current_file_path_, std::ios::app); // 以追加模式打开日志文件
}

//写入日志到文件
void Logger::writeLogToFile(const std::string& message) {
    rotateLogFileIfNeeded();
    if (log_file_.is_open()) {
        log_file_ << message;
    }
}

//检查并轮转日志文件
void Logger::rotateLogFileIfNeeded(){
    if(!std::filesystem::exists(current_file_path_)) {
        openLogFile(); // 如果文件不存在，打开新文件
        return; // 如果文件不存在，直接返回
    }
    if(std::filesystem::file_size(current_file_path_) >= config_.max_file_size) {
      current_file_path=getNewLogFilePath(); // 获取新的日志文件路径
        openLogFile(); // 打开新的日志文件
        cleanOldLogFiles(); // 清理旧的日志文件
    }
}
//写入日志到文件(异步)
void Logger::writeToFile(const std::string& message) {
    if(config_.async_mode) { // 如果启用异步模式
        std::unique_lock<std::mutex> lock(log_mutex_); // 加锁
        log_queue_.push(message); // 将日志消息放入队列
        log_condition_.notify_one(); // 通知处理线程
    } else {
        writeLogToFile(message); // 直接写入日志文件
    }
}
//处理日志队列(异步)
void Logger::processLogQueue(){
    std::vector<std::string> batch; // 批量处理的日志消息
    batch.reserve(BATCH_PROCESSING_THRESHOLD); // 预留空间
    while(ture){
        std::unique_lock<std::mutex> lock(log_mutex_); // 加锁
        if(!log_queue_.empty()){
            while(!log_queue_.empty()&&batch.size()<BATCH_PROCESSING_THRESHOLD){
                batch.push_back(std::move(log_queue_.front())); // 将队列中的日志消息放入批量处理
                log_queue_.pop(); // 移除队列中的日志消息
            }
            lock.unlock(); // 解锁
            if(!batch.empty()){
                std::string buffer;
                size_t total_size=0;
                for(const auto& msg:batch){
                    total_size+=msg.size();// 将批量处理的日志消息拼接
                }
                buffer.reserve(total_size); // 预留空间
                for(const auto& msg:batch){
                    buffer.append(msg); // 拼接日志消息
                }
                writeLogToFile(buffer); // 写入日志文件
                batch.clear(); // 清空批量处理的日志消息
            }
        }else{
            queue_cv_.wait(lock,[this]{
                return !log_queue_.empty()||stop_thread_; // 等待通知
            }); // 等待通知
            if(stop_thread_&&log_queue_.empty()){
                break; // 如果停止线程且队列为空，退出循环
            }
        }
    }
}

//清理旧的日志文件
void Logger::cleanOldLogFiles(){
    std::vector<std::filesystem::path> log_files;
    for(const auto& entry:std::filesystem::directory_iterator(config_.log_dir)){
        if(entry.path().extension() == ".log"){
            log_files.push_back(entry.path()); // 添加日志文件到列表
        }
    }
    if(log_files.size() > config_.max_log_files){
        std::sort(log_files.begin(),log_files.end()); // 排序
        for(size_t i = 0; i < log_files.size() - config_.max_files; ++i){
            std::filesystem::remove(log_files[i]); // 删除旧的日志文件
        }
    }

}

//获取新的日志文件路径
std::string Logger::getNewLogFilePath(){
    std::filesystem::create_directories(config_.log_dir); // 创建日志目录
    auto now= std::chrono::system_clock::now(); // 获取当前时间
    auto now_time=std::chrono::system_clock::to_time_t(now); // 转换为 time_t 类型
    std::stringstream ss;
    ss<<config_.log_dir<<"/"
    << std::put_time(std::localtime(&now_time_t), "%Y%m%d_%H%M%S") 
       << ".log";
    return ss.str(); // 返回格式化后的日志文件路径
}
//析构函数
Logger::~Logger(){
    if(config_.async_mode){
        stop_thread_=true; // 设置停止线程标志
        queue_cv_.notify_one(); // 通知处理线程
        if(write_thread_.joinable()){
            write_thread_.join(); // 等待处理线程结束
        }
    }

}

} // namespace utils