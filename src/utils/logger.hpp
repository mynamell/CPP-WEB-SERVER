#pragma once
#include <iostream>
#include <string>
#include <sstream>
#include <queue>
#include <mutex>
#include <thread>
#include <condition_variable>

namespace utils{
/////////////////创建结构体////////////////////////
// 日志级别的枚举类型，用于表示日志的严重程度
enum class LogLevel {
    DEBUG = 0, // 调试信息
    INFO = 1,  // 普通信息
    WARN = 2,  // 警告信息
    ERROR = 3, // 错误信息
    FATAL = 4  // 致命错误信息
};

// 日志配置结构体，用于存储日志系统的配置参数
struct LogConfig{
    std::string log_dir;      // 日志文件存储目录
    size_t max_file_size;     // 单个日志文件的最大大小（字节）
    size_t max_files;         // 最大日志文件数量
    bool async_mode;          // 是否启用异步模式

    // 默认构造函数，初始化默认配置
    LogConfig()
        : log_dir("logs"),
          max_file_size(10 * 1024 * 1024), // 默认10MB
          max_files(10),
          async_mode(true) {}
};

class Logger {
public:
    //构造函数，初始化日志流
    class LogStream {
        public:
        // 构造函数，初始化日志流
        LogStream(LogLevel level, const char* file, const char* function, int line);
        // 析构函数，负责将日志写入
        ~LogStream();
         // 重载 << 运算符，用于支持流式日志记录
         template<typename T>
         LogStream& operator<<(const T& value) {
             if (level_ >= Logger::getGlobalLevel()) { // 仅记录高于全局日志级别的日志
                 stream_ << value;
             }
             return *this;
         }  
        private:
            LogLevel level; // 日志级别
            std::ostringstream &stream;//存储日志内容的字符串流
    };
      // 初始化日志系统，传入日志配置
      static void init(const LogConfig& config = LogConfig());
      // 设置全局日志级别
      static void setGlobalLevel(LogLevel level);
      // 记录日志，提供日志级别、文件名、函数名、行号和日志消息
      static void log(LogLevel level, const char* file, const char* function, int line, const std::string& message);

private:
    //私有构造函数
    Logger()=default;
    //析构
    ~Logger();
    //获取日志实例
    static Logger& getGlobaLevel();
    // 初始化日志系统
    void initLogger(const LogConfig& config);
    // 将日志写入文件
    void writeToFile(const std::string& message);
    // 处理日志队列（异步模式下使用）
    void processLogQueue();
    // 将日志写入文件（内部调用）
    void writeLogToFile(const std::string& message);
    // 检查并根据需要进行日志文件轮转
    void rotateLogFileIfNeeded();
    // 清理旧的日志文件
    void cleanOldLogFiles();
    // 获取新的日志文件路径
    std::string getNewLogFilePath();
    // 打开日志文件
    void openLogFile();

    static LogLevel global_log_level; // 全局日志级别
    LogConfig log_config; // 日志配置
    std::string current_file_path; // 当前日志文件路径
    std::queue<std::string> log_queue; // 日志队列
    std::mutex queue_mutex; // 互斥锁，用于保护日志队列
    std::condition_variable queue_cond; // 条件变量，用于通知日志线程
    std::thread log_write_thread; // 日志写入线程
    bool stop_thread=false; // 标志，指示是否停止日志线程
    std::ofstream log_file; // 日志文件流
};

}

// 定义便捷宏，用于快速记录不同级别的日志
#define LOG_DEBUG utils::Logger::LogStream(utils::LogLevel::DEBUG, __FILE__, __FUNCTION__, __LINE__)
#define LOG_INFO  utils::Logger::LogStream(utils::LogLevel::INFO, __FILE__, __FUNCTION__, __LINE__)
#define LOG_WARN  utils::Logger::LogStream(utils::LogLevel::WARN, __FILE__, __FUNCTION__, __LINE__)
#define LOG_ERROR utils::Logger::LogStream(utils::LogLevel::ERROR, __FILE__, __FUNCTION__, __LINE__)
#define LOG_FATAL utils::Logger::LogStream(utils::LogLevel::FATAL, __FILE__, __FUNCTION__, __LINE__)
