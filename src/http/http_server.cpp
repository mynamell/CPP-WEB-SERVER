#include "http_server.hpp"
#include <iostream>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

namespace http{


    int HttpServer::setNonBlocking(int fd){
        int flags = fcntl(fd, F_GETFL, 0);
        if (flags == -1) {
            return -1;
        }
        return fcntl(fd, F_SETFL, flags|O_NONBLOCK);
    }

HttpServer::HttpServer(int port)
   :port(port),running(false),static_dir("")
   {
    static_dir = "./";
    server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd < 0) {
        throw std::runtime_error("Failed to create socket");
    }
    int opt = 1;//值为1启用下面端口0禁用
    //SO_REUSEADDR（允许套接字关闭后立即重新使用本地地址）允许端口复用SOL_SOCKET套接字级别选项opt指向选项值得指针
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt))) {//非0值选项设置失败
        throw std::runtime_error("Failed to set socket options");
    }
    sockaddr_in address{};
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(port);

  if (bind(server_fd, (struct sockaddr*)&address, sizeof(address)) < 0) {
        throw std::runtime_error("Failed to bind to port");
    }
    
    if (listen(server_fd, 10) < 0) {
        throw std::runtime_error("Failed to listen");
    }
   }

HttpServer::~HttpServer() {
         stop();
         close(server_fd);
    }
   
void HttpServer::run() {
        running = true;
        LOG_INFO << "Server listening on port " << port;
    
        fd_set read_fds;
        int max_fd = server_fd;
        std::vector<int> client_fds;  // 存储所有客户端fd
    
        while (running) {
            FD_ZERO(&read_fds);
            FD_SET(server_fd, &read_fds);
    
            // 添加所有客户端fd到监听集合
            for (int fd : client_fds) {
                FD_SET(fd, &read_fds);
                if (fd > max_fd) max_fd = fd;
            }
    
            // 设置1秒超时（可检测running状态）
            struct timeval timeout;
            timeout.tv_sec = 1;
            timeout.tv_usec = 0;
    
            int activity = select(max_fd + 1, &read_fds, nullptr, nullptr, &timeout);
            
            if (activity < 0 && errno != EINTR) {
                LOG_ERROR << "select error: " << strerror(errno);
                continue;
            }
    
            // 检查新连接
            if (FD_ISSET(server_fd, &read_fds)) {
                sockaddr_in client_addr{};
                socklen_t addr_len = sizeof(client_addr);
                int client_fd = accept(server_fd, (struct sockaddr*)&client_addr, &addr_len);
                
                if (client_fd < 0) {
                    LOG_ERROR << "Accept failed: " << strerror(errno);
                    continue;
                }
    
               
    
                // 记录并存储新连接
                LOG_INFO << "New connection from " << inet_ntoa(client_addr.sin_addr) 
                        << ":" << ntohs(client_addr.sin_port);
                client_fds.push_back(client_fd);
            }
    
            // 检查客户端数据
            for (auto it = client_fds.begin(); it != client_fds.end(); ) {
                int fd = *it;
                if (FD_ISSET(fd, &read_fds)) {
                    char buffer[1024];
                    ssize_t bytes = recv(fd, buffer, sizeof(buffer), 0);
    
                    if (bytes <= 0) {  // 连接关闭或错误
                        if (bytes == 0) {
                            LOG_INFO << "Client disconnected (fd: " << fd << ")";
                        } else {
                            LOG_ERROR << "Recv error: " << strerror(errno);
                        }
                        close(fd);
                        it = client_fds.erase(it);
                        continue;
                    } else {  // 处理请求
                        thread_pool.enqueue([this, fd, buf = std::string(buffer, bytes)] {
                            HttpRequest req = HttpRequest::parse(buf);
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
    
                            std::string response_str = res.toString();
                            send(fd, response_str.c_str(), response_str.size(), 0);
                            close(fd);
                            return 0;
                        });
                        it = client_fds.erase(it);
                    }
                } else {
                    ++it;
                }
            }
        }
    
        // 清理所有客户端连接
        for (int fd : client_fds) close(fd);
        close(server_fd);
    }
    
void HttpServer::stop() {
    running = false;
}
void HttpServer::addHandler(const std::string& path, const std::string& method, RequestHandler handler) {
    handlers[path][method] = std::move(handler);
}

void HttpServer::handlerClient(int client_fd)
{
    //读取客户端请求
    char buffer[4096];
    ssize_t bytes_read=read(client_fd,buffer,sizeof(buffer)-1);
    if(bytes_read>0){
        buffer[bytes_read]='\0';//添加结束符
        std::count<<"Received request: "<<buffer<<std::endl;
        //解析 HTTP 请求
        HttpRequest request=HttpRequest::parse(buffer);
        HttpResponse response;
        //路由匹配与请求
        auto path_it=handlers.find(request.path);
        if(path_it!=handlers.end()){
            auto method_it=path_it->second.find(request.method);
            if(method_it!=path_it->second.end()){
                response=method_it->second(request);
            }else{
                response=HttpResponse(405,"Method Not Allowed");
            }
    }else if(request.path=="/"||request.path.find('.')!=std::string::npos){
        std::string path = request.path == "/" ? "/index.html" : request.path;
        std::string full_path = static_dir + path;
        serveStaticFile(full_path, response);
    }else{
        response=HttpResponse(404,"Not Found");
    }
    response.headers["Access-Control-Allow-Origin"] = "*";
    response.headers["Access-Control-Allow-Methods"] = "GET, POST, OPTIONS";
    response.headers["Access-Control-Allow-Headers"] = "Content-Type";
    std::string response_str = response.toString();
    ssize_t total_bytes_written = 0;
    const char* data = response_str.c_str();
    size_t remaining = response_str.length();

    while (remaining > 0) {
    ssize_t bytes_written = write(client_fd, data + total_bytes_written, remaining);
        if (bytes_written < 0) {
            if (errno == EINTR) {
                continue;  // 如果被信号中断，重试
            }
        break;
        }
        total_bytes_written += bytes_written;
        remaining -= bytes_written;
    }
    close(client_fd);
}

void HttpServer::serveStaticFile(const std::string& path, HttpResponse& response) {
    struct stat sb;
    if (stat(path.c_str(), &sb) != 0) {
        LOG_ERROR << "File not found: " << path;
        response = HttpResponse(404, "File not found");
        return;
    }
    
    std::string ext = path.substr(path.find_last_of('.') + 1);//获取扩展名确定文件类型
    
    // Set appropriate Content-Type header
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
        response.headers["Content-Type"] = mimeType->second;
    } else {
        response.headers["Content-Type"] = "application/octet-stream";
    }
    
    // Read file content
    std::ifstream file(path, std::ios::binary);//二进制模式打开文件
    if (!file) {
        LOG_ERROR << "Failed to open file: " << path;
        response = HttpResponse(500, "Internal Server Error");
        return;
    }
    
    std::stringstream buffer;
    buffer << file.rdbuf();
    response.status_code = 200;
    response.body = buffer.str();
    
    // Add caching headers for static files
    response.headers["Cache-Control"] = "public, max-age=86400"; // Cache for 24 hours
    
    // Add security headers
    response.headers["X-Content-Type-Options"] = "nosniff";//[防止浏览器猜测文件类型]
    response.headers["X-Frame-Options"] = "SAMEORIGIN";//[防止点击劫持攻击]
    response.headers["X-XSS-Protection"] = "1; mode=block";//[启用 XSS 过滤]
    
    // Add CORS headers
    response.headers["Access-Control-Allow-Origin"] = "*";
    response.headers["Access-Control-Allow-Methods"] = "GET, POST, OPTIONS";
    response.headers["Access-Control-Allow-Headers"] = "Content-Type";
}

}