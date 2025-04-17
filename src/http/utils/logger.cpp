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
//获取本地线程字符串流
static std::ostringstream& getThreadLocalStream() {
    thread_local std::ostringstream stream;
    sstream.str("");
    sstream.clear();
    return stream;
}