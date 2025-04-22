// 包含HTTP服务器的头文件，用于处理HTTP请求和响应
#include "http/http_server.hpp"

// 包含数据库管理器的头文件，用于与数据库交互
#include "db/database_manager.hpp"

#include <memory>  // 用于智能指针（std::unique_ptr）
#include <string>  // 用于std::string类型

// ChatApplication类定义，表示一个聊天应用程序
class ChatApplication {
public:
    // 构造函数，接受静态文件目录路径作为参数
    ChatApplication(const std::string& static_dir);

    // 启动聊天应用程序，监听指定端口
    void start(int port);

    // 停止聊天应用程序
    void stop();

private:
    // 设置HTTP路由，用于处理不同的HTTP请求
    void setupRoutes();

    // 提供静态文件服务，返回指定路径的文件内容
    http::HttpResponse serveStaticFile(const std::string& path);

    // 静态文件目录路径
    std::string static_dir_;

    // HTTP服务器实例，用于处理HTTP请求
    std::unique_ptr<http::HttpServer> http_server_;

    // 数据库管理器实例，用于与数据库交互
    std::unique_ptr<db::DatabaseManager> db_manager_;
};