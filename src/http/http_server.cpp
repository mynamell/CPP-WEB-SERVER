#include "http_server.hpp"
#include <iostream>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

namespace http {

int HttpServer::setNonBlocking(int fd) {
    // 获取文件描述符的当前标志
    int flags = fcntl(fd, F_GETFL, 0);
    if (flags == -1) {
        return -1; // 如果获取失败，返回 -1
    }
    // 设置文件描述符为非阻塞模式
    return fcntl(fd, F_SETFL, flags | O_NONBLOCK);
}

HttpServer::HttpServer(int port)
    : port(port), running(false), static_dir("") {
    static_dir = "./"; // 设置静态文件目录为当前目录

    // 创建套接字，使用 IPv4 和 TCP 协议
    server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd < 0) {
        throw std::runtime_error("Failed to create socket"); // 如果失败，抛出异常
    }

    int opt = 1; // 设置选项值为 1
    // 设置套接字选项，允许端口复用
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt))) {
        throw std::runtime_error("Failed to set socket options"); // 如果失败，抛出异常
    }

    sockaddr_in address{}; // 定义地址结构
    address.sin_family = AF_INET; // 使用 IPv4
    address.sin_addr.s_addr = INADDR_ANY; // 绑定到所有可用的网络接口
    address.sin_port = htons(port); // 设置端口号，使用网络字节序

    // 绑定套接字到指定地址和端口
    if (bind(server_fd, (struct sockaddr*)&address, sizeof(address)) < 0) {
        throw std::runtime_error("Failed to bind to port"); // 如果失败，抛出异常
    }

    // 开始监听，最大连接队列长度为 10
    if (listen(server_fd, 10) < 0) {
        throw std::runtime_error("Failed to listen"); // 如果失败，抛出异常
    }
}

HttpServer::~HttpServer() {
    stop(); // 停止服务器
    close(server_fd); // 关闭服务器套接字
}

void HttpServer::run() {
    running = true; // 设置服务器为运行状态
    LOG_INFO << "Server listening on port " << port; // 打印日志

    fd_set read_fds; // 定义文件描述符集合
    int max_fd = server_fd; // 初始化最大文件描述符为服务器套接字
    std::vector<int> client_fds; // 存储所有客户端文件描述符

    while (running) {
        FD_ZERO(&read_fds); // 清空文件描述符集合
        FD_SET(server_fd, &read_fds); // 将服务器套接字加入集合

        // 添加所有客户端文件描述符到集合
        for (int fd : client_fds) {
            FD_SET(fd, &read_fds);
            if (fd > max_fd) max_fd = fd; // 更新最大文件描述符
        }

        // 设置超时时间为 1 秒
        struct timeval timeout;
        timeout.tv_sec = 1;
        timeout.tv_usec = 0;

        // 使用 select 监听文件描述符集合
        int activity = select(max_fd + 1, &read_fds, nullptr, nullptr, &timeout);

        if (activity < 0 && errno != EINTR) {
            LOG_ERROR << "select error: " << strerror(errno); // 如果出错，打印日志
            continue;
        }

        // 检查是否有新连接
        if (FD_ISSET(server_fd, &read_fds)) {
            sockaddr_in client_addr{};
            socklen_t addr_len = sizeof(client_addr);
            // 接受新连接
            int client_fd = accept(server_fd, (struct sockaddr*)&client_addr, &addr_len);

            if (client_fd < 0) {
                LOG_ERROR << "Accept failed: " << strerror(errno); // 如果失败，打印日志
                continue;
            }

            // 打印新连接的客户端信息
            LOG_INFO << "New connection from " << inet_ntoa(client_addr.sin_addr)
                     << ":" << ntohs(client_addr.sin_port);
            client_fds.push_back(client_fd); // 将新连接加入客户端列表
        }

        // 检查客户端数据
        for (auto it = client_fds.begin(); it != client_fds.end();) {
            int fd = *it;
            if (FD_ISSET(fd, &read_fds)) {
                char buffer[1024]; // 定义缓冲区
                ssize_t bytes = recv(fd, buffer, sizeof(buffer), 0); // 接收数据

                if (bytes <= 0) { // 如果连接关闭或出错
                    if (bytes == 0) {
                        LOG_INFO << "Client disconnected (fd: " << fd << ")"; // 打印日志
                    } else {
                        LOG_ERROR << "Recv error: " << strerror(errno); // 打印错误日志
                    }
                    close(fd); // 关闭客户端连接
                    it = client_fds.erase(it); // 从客户端列表中移除
                    continue;
                } else { // 如果接收到数据
                    // 将请求交给线程池处理
                    thread_pool.enqueue([this, fd, buf = std::string(buffer, bytes)] {
                        HttpRequest req = HttpRequest::parse(buf); // 解析请求
                        auto path_it = handlers.find(req.path);
                        HttpResponse res;

                        if (path_it != handlers.end()) {
                            auto method_it = path_it->second.find(req.method);
                            res = (method_it != path_it->second.end())
                                      ? method_it->second(req)
                                      : HttpResponse(405, "Method Not Allowed");
                        } else {
                            res = HttpResponse(404, "Not Found");
                        }

                        std::string response_str = res.toString(); // 转换响应为字符串
                        send(fd, response_str.c_str(), response_str.size(), 0); // 发送响应
                        close(fd); // 关闭客户端连接
                        return 0;
                    });
                    it = client_fds.erase(it); // 从客户端列表中移除
                }
            } else {
                ++it; // 检查下一个客户端
            }
        }
    }

    // 清理所有客户端连接
    for (int fd : client_fds) close(fd);
    close(server_fd); // 关闭服务器套接字
}

void HttpServer::stop() {
    running = false; // 设置服务器为停止状态
}

void HttpServer::addHandler(const std::string& path, const std::string& method, RequestHandler handler) {
    handlers[path][method] = std::move(handler); // 添加路由处理器
}

void HttpServer::handlerClient(int client_fd) {
    // 读取客户端请求
    char buffer[4096];
    ssize_t bytes_read = read(client_fd, buffer, sizeof(buffer) - 1);
    if (bytes_read > 0) {
        buffer[bytes_read] = '\0'; // 添加结束符
        std::cout << "Received request: " << buffer << std::endl; // 打印请求

        // 解析 HTTP 请求
        HttpRequest request = HttpRequest::parse(buffer);
        HttpResponse response;

        // 路由匹配与请求处理
        auto path_it = handlers.find(request.path);
        if (path_it != handlers.end()) {
            auto method_it = path_it->second.find(request.method);
            if (method_it != path_it->second.end()) {
                response = method_it->second(request);
            } else {
                response = HttpResponse(405, "Method Not Allowed");
            }
        } else if (request.path == "/" || request.path.find('.') != std::string::npos) {
            std::string path = request.path == "/" ? "/index.html" : request.path;
            std::string full_path = static_dir + path;
            serveStaticFile(full_path, response);
        } else {
            response = HttpResponse(404, "Not Found");
        }

        // 添加 CORS 头
        response.headers["Access-Control-Allow-Origin"] = "*";
        response.headers["Access-Control-Allow-Methods"] = "GET, POST, OPTIONS";
        response.headers["Access-Control-Allow-Headers"] = "Content-Type";

        // 发送响应
        std::string response_str = response.toString();
        ssize_t total_bytes_written = 0;
        const char* data = response_str.c_str();
        size_t remaining = response_str.length();

        while (remaining > 0) {
            ssize_t bytes_written = write(client_fd, data + total_bytes_written, remaining);
            if (bytes_written < 0) {
                if (errno == EINTR) {
                    continue; // 如果被信号中断，重试
                }
                break;
            }
            total_bytes_written += bytes_written;
            remaining -= bytes_written;
        }
        close(client_fd); // 关闭客户端连接
    }
}

void HttpServer::serveStaticFile(const std::string& path, HttpResponse& response) {
    struct stat sb;
    if (stat(path.c_str(), &sb) != 0) {
        LOG_ERROR << "File not found: " << path; // 如果文件不存在，打印日志
        response = HttpResponse(404, "File not found");
        return;
    }

    std::string ext = path.substr(path.find_last_of('.') + 1); // 获取文件扩展名

    // 设置 Content-Type 头
    const std::unordered_map<std::string, std::string> mimeTypes = {
        {"html", "text/html"},
        {"css", "text/css"},
        {"js", "application/javascript"},
        {"json", "application/json"},
        {"png", "image/png"},
        {"jpg", "image/jpeg"},
        {"jpeg", "image/jpeg"},
        {"gif", "image/gif"},
        {"svg", "image/svg+xml"},
        {"ico", "image/x-icon"},
        {"txt", "text/plain"},
        {"pdf", "application/pdf"},
        {"zip", "application/zip"},
        {"woff", "font/woff"},
        {"woff2", "font/woff2"},
        {"ttf", "font/ttf"},
        {"eot", "application/vnd.ms-fontobject"},
        {"mp3", "audio/mpeg"},
        {"mp4", "video/mp4"},
        {"webm", "video/webm"},
        {"webp", "image/webp"}
    };

    auto mimeType = mimeTypes.find(ext);
    if (mimeType != mimeTypes.end()) {
        response.headers["Content-Type"] = mimeType->second; // 设置 MIME 类型
    } else {
        response.headers["Content-Type"] = "application/octet-stream"; // 默认类型
    }

    // 读取文件内容
    std::ifstream file(path, std::ios::binary); // 二进制模式打开文件
    if (!file) {
        LOG_ERROR << "Failed to open file: " << path; // 如果打开失败，打印日志
        response = HttpResponse(500, "Internal Server Error");
        return;
    }

    std::stringstream buffer;
    buffer << file.rdbuf(); // 将文件内容读入缓冲区
    response.status_code = 200;
    response.body = buffer.str(); // 设置响应体

    // 添加缓存头
    response.headers["Cache-Control"] = "public, max-age=86400"; // 缓存 24 小时

    // 添加安全头
    response.headers["X-Content-Type-Options"] = "nosniff"; // 防止浏览器猜测文件类型
    response.headers["X-Frame-Options"] = "SAMEORIGIN"; // 防止点击劫持攻击
    response.headers["X-XSS-Protection"] = "1; mode=block"; // 启用 XSS 过滤

    // 添加 CORS 头
    response.headers["Access-Control-Allow-Origin"] = "*";
    response.headers["Access-Control-Allow-Methods"] = "GET, POST, OPTIONS";
    response.headers["Access-Control-Allow-Headers"] = "Content-Type";
}

#include "http_server.hpp"
#include <iostream>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

namespace http {

int HttpServer::setNonBlocking(int fd) {
    // 获取文件描述符的当前标志
    int flags = fcntl(fd, F_GETFL, 0);
    if (flags == -1) {
        return -1; // 如果获取失败，返回 -1
    }
    // 设置文件描述符为非阻塞模式
    return fcntl(fd, F_SETFL, flags | O_NONBLOCK);
}

HttpServer::HttpServer(int port)
    : port(port), running(false), static_dir("") {
    static_dir = "./"; // 设置静态文件目录为当前目录

    // 创建套接字，使用 IPv4 和 TCP 协议
    server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd < 0) {
        throw std::runtime_error("Failed to create socket"); // 如果失败，抛出异常
    }

    int opt = 1; // 设置选项值为 1
    // 设置套接字选项，允许端口复用
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt))) {
        throw std::runtime_error("Failed to set socket options"); // 如果失败，抛出异常
    }

    sockaddr_in address{}; // 定义地址结构
    address.sin_family = AF_INET; // 使用 IPv4
    address.sin_addr.s_addr = INADDR_ANY; // 绑定到所有可用的网络接口
    address.sin_port = htons(port); // 设置端口号，使用网络字节序

    // 绑定套接字到指定地址和端口
    if (bind(server_fd, (struct sockaddr*)&address, sizeof(address)) < 0) {
        throw std::runtime_error("Failed to bind to port"); // 如果失败，抛出异常
    }

    // 开始监听，最大连接队列长度为 10
    if (listen(server_fd, 10) < 0) {
        throw std::runtime_error("Failed to listen"); // 如果失败，抛出异常
    }
}

HttpServer::~HttpServer() {
    stop(); // 停止服务器
    close(server_fd); // 关闭服务器套接字
}

void HttpServer::run() {
    running = true; // 设置服务器为运行状态
    LOG_INFO << "Server listening on port " << port; // 打印日志

    fd_set read_fds; // 定义文件描述符集合
    int max_fd = server_fd; // 初始化最大文件描述符为服务器套接字
    std::vector<int> client_fds; // 存储所有客户端文件描述符

    while (running) {
        FD_ZERO(&read_fds); // 清空文件描述符集合
        FD_SET(server_fd, &read_fds); // 将服务器套接字加入集合

        // 添加所有客户端文件描述符到集合
        for (int fd : client_fds) {
            FD_SET(fd, &read_fds);
            if (fd > max_fd) max_fd = fd; // 更新最大文件描述符
        }

        // 设置超时时间为 1 秒
        struct timeval timeout;
        timeout.tv_sec = 1;
        timeout.tv_usec = 0;

        // 使用 select 监听文件描述符集合
        int activity = select(max_fd + 1, &read_fds, nullptr, nullptr, &timeout);

        if (activity < 0 && errno != EINTR) {
            LOG_ERROR << "select error: " << strerror(errno); // 如果出错，打印日志
            continue;
        }

        // 检查是否有新连接
        if (FD_ISSET(server_fd, &read_fds)) {
            sockaddr_in client_addr{};
            socklen_t addr_len = sizeof(client_addr);
            // 接受新连接
            int client_fd = accept(server_fd, (struct sockaddr*)&client_addr, &addr_len);

            if (client_fd < 0) {
                LOG_ERROR << "Accept failed: " << strerror(errno); // 如果失败，打印日志
                continue;
            }

            // 打印新连接的客户端信息
            LOG_INFO << "New connection from " << inet_ntoa(client_addr.sin_addr)
                     << ":" << ntohs(client_addr.sin_port);
            client_fds.push_back(client_fd); // 将新连接加入客户端列表
        }

        // 检查客户端数据
        for (auto it = client_fds.begin(); it != client_fds.end();) {
            int fd = *it;
            if (FD_ISSET(fd, &read_fds)) {
                char buffer[1024]; // 定义缓冲区
                ssize_t bytes = recv(fd, buffer, sizeof(buffer), 0); // 接收数据

                if (bytes <= 0) { // 如果连接关闭或出错
                    if (bytes == 0) {
                        LOG_INFO << "Client disconnected (fd: " << fd << ")"; // 打印日志
                    } else {
                        LOG_ERROR << "Recv error: " << strerror(errno); // 打印错误日志
                    }
                    close(fd); // 关闭客户端连接
                    it = client_fds.erase(it); // 从客户端列表中移除
                    continue;
                } else { // 如果接收到数据
                    // 将请求交给线程池处理
                    thread_pool.enqueue([this, fd, buf = std::string(buffer, bytes)] {
                        HttpRequest req = HttpRequest::parse(buf); // 解析请求
                        auto path_it = handlers.find(req.path);
                        HttpResponse res;

                        if (path_it != handlers.end()) {
                            auto method_it = path_it->second.find(req.method);
                            res = (method_it != path_it->second.end())
                                      ? method_it->second(req)
                                      : HttpResponse(405, "Method Not Allowed");
                        } else {
                            res = HttpResponse(404, "Not Found");
                        }

                        std::string response_str = res.toString(); // 转换响应为字符串
                        send(fd, response_str.c_str(), response_str.size(), 0); // 发送响应
                        close(fd); // 关闭客户端连接
                        return 0;
                    });
                    it = client_fds.erase(it); // 从客户端列表中移除
                }
            } else {
                ++it; // 检查下一个客户端
            }
        }
    }

    // 清理所有客户端连接
    for (int fd : client_fds) close(fd);
    close(server_fd); // 关闭服务器套接字
}

void HttpServer::stop() {
    running = false; // 设置服务器为停止状态
}

void HttpServer::addHandler(const std::string& path, const std::string& method, RequestHandler handler) {
    handlers[path][method] = std::move(handler); // 添加路由处理器
}

void HttpServer::handlerClient(int client_fd) {
    // 读取客户端请求
    char buffer[4096];
    ssize_t bytes_read = read(client_fd, buffer, sizeof(buffer) - 1);
    if (bytes_read > 0) {
        buffer[bytes_read] = '\0'; // 添加结束符
        std::cout << "Received request: " << buffer << std::endl; // 打印请求

        // 解析 HTTP 请求
        HttpRequest request = HttpRequest::parse(buffer);
        HttpResponse response;

        // 路由匹配与请求处理
        auto path_it = handlers.find(request.path);
        if (path_it != handlers.end()) {
            auto method_it = path_it->second.find(request.method);
            if (method_it != path_it->second.end()) {
                response = method_it->second(request);
            } else {
                response = HttpResponse(405, "Method Not Allowed");
            }
        } else if (request.path == "/" || request.path.find('.') != std::string::npos) {
            std::string path = request.path == "/" ? "/index.html" : request.path;
            std::string full_path = static_dir + path;
            serveStaticFile(full_path, response);
        } else {
            response = HttpResponse(404, "Not Found");
        }

        // 添加 CORS 头
        response.headers["Access-Control-Allow-Origin"] = "*";
        response.headers["Access-Control-Allow-Methods"] = "GET, POST, OPTIONS";
        response.headers["Access-Control-Allow-Headers"] = "Content-Type";

        // 发送响应
        std::string response_str = response.toString();
        ssize_t total_bytes_written = 0;
        const char* data = response_str.c_str();
        size_t remaining = response_str.length();

        while (remaining > 0) {
            ssize_t bytes_written = write(client_fd, data + total_bytes_written, remaining);
            if (bytes_written < 0) {
                if (errno == EINTR) {
                    continue; // 如果被信号中断，重试
                }
                break;
            }
            total_bytes_written += bytes_written;
            remaining -= bytes_written;
        }
        close(client_fd); // 关闭客户端连接
    }
}

void HttpServer::serveStaticFile(const std::string& path, HttpResponse& response) {
    struct stat sb;
    if (stat(path.c_str(), &sb) != 0) {
        LOG_ERROR << "File not found: " << path; // 如果文件不存在，打印日志
        response = HttpResponse(404, "File not found");
        return;
    }

    std::string ext = path.substr(path.find_last_of('.') + 1); // 获取文件扩展名

    // 设置 Content-Type 头
    const std::unordered_map<std::string, std::string> mimeTypes = {
        {"html", "text/html"},
        {"css", "text/css"},
        {"js", "application/javascript"},
        {"json", "application/json"},
        {"png", "image/png"},
        {"jpg", "image/jpeg"},
        {"jpeg", "image/jpeg"},
        {"gif", "image/gif"},
        {"svg", "image/svg+xml"},
        {"ico", "image/x-icon"},
        {"txt", "text/plain"},
        {"pdf", "application/pdf"},
        {"zip", "application/zip"},
        {"woff", "font/woff"},
        {"woff2", "font/woff2"},
        {"ttf", "font/ttf"},
        {"eot", "application/vnd.ms-fontobject"},
        {"mp3", "audio/mpeg"},
        {"mp4", "video/mp4"},
        {"webm", "video/webm"},
        {"webp", "image/webp"}
    };

    auto mimeType = mimeTypes.find(ext);
    if (mimeType != mimeTypes.end()) {
        response.headers["Content-Type"] = mimeType->second; // 设置 MIME 类型
    } else {
        response.headers["Content-Type"] = "application/octet-stream"; // 默认类型
    }

    // 读取文件内容
    std::ifstream file(path, std::ios::binary); // 二进制模式打开文件
    if (!file) {
        LOG_ERROR << "Failed to open file: " << path; // 如果打开失败，打印日志
        response = HttpResponse(500, "Internal Server Error");
        return;
    }

    std::stringstream buffer;
    buffer << file.rdbuf(); // 将文件内容读入缓冲区
    response.status_code = 200;
    response.body = buffer.str(); // 设置响应体

    // 添加缓存头
    response.headers["Cache-Control"] = "public, max-age=86400"; // 缓存 24 小时

    // 添加安全头
    response.headers["X-Content-Type-Options"] = "nosniff"; // 防止浏览器猜测文件类型
    response.headers["X-Frame-Options"] = "SAMEORIGIN"; // 防止点击劫持攻击
    response.headers["X-XSS-Protection"] = "1; mode=block"; // 启用 XSS 过滤

    // 添加 CORS 头
    response.headers["Access-Control-Allow-Origin"] = "*";
    response.headers["Access-Control-Allow-Methods"] = "GET, POST, OPTIONS";
    response.headers["Access-Control-Allow-Headers"] = "Content-Type";
}

}}