#pragma once
#include <string>
#include <functional>
#include "utils/thread_pool.hpp"
#include "http/http_request.hpp"
#include "http/http_response.hpp"

namespace http{

class HttpServer{
    public:
        using RequestHandler=std::function<HttpResponse(const HttpRequest&)>;
        explicit HttpServer(int port=8080);
        ~HttpServer();

        void addHandler(const std::string& path, const std::string& method, RequestHandler handler);
        void run();
        void stop();
    private:
        int server_fd;
        int port;
        
        bool running;
        std::unordered_map<std::string, std::unordered_map<std::string, RequestHandler>> handlers;
        std::string static_dir;
        void handlerClient(int client_fd);//处理客户端请求
        int setNonBlocking(int fd);//声明epoll为非堵塞
    };

    

}