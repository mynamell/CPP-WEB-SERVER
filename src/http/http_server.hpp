#pragma once
#include <string>
#include <unordered_map>
#include <sys/epoll.h> // 引入 epoll 相关头文件
#include "utils/thread_pool.hpp"
#include "http/http_request.hpp"
#include "http/http_response.hpp"

namespace http {

class HttpServer {
public:
    using RequestHandler = std::function<HttpResponse(const HttpRequest&)>;
    explicit HttpServer(int port = 8080);
    ~HttpServer();

    void addHandler(const std::string& path, const std::string& method, RequestHandler handler);
    void run();
    void stop();

private:
    int server_fd;
    int epoll_fd; // epoll 文件描述符
    int port;
    bool running;
    std::unordered_map<std::string, std::unordered_map<std::string, RequestHandler>> handlers;
    std::string static_dir;

    void handlerClient(int client_fd); // 处理客户端请求
    int setNonBlocking(int fd);        // 设置非阻塞模式
    void handleEpollEvents(epoll_event* events, int num_events); // 处理 epoll 事件
};

}