#include "http/http_request.hpp"
#include <sstream>

namespace http{
    HttpRequest::HttpRequest(int code,const std::string& body)
    :status_code(code),body(body)
    {
        if(body.empty()||(body[0]=='{'&&body.back()=='}')||(body[0]=='['&&body.back()==']')){
            headers["Content-Type"]="application/json";
            if(!body.empty()&&body[0]!='{}'&&body[0]!='['){
                this->body="{\"message\":\""+body+"\"}";
            }
        }else{
            headers["Content-Type"]="text/plain";
        } 
    }
    std::string HttpResponse::to_string() const{
        std::stringstream ss;//创建字符串流
        //添加状态行
        ss << "HTTP/1.1 " << status_code << " " << getStatusText(status_code) << "\r\n";
        //添加Content-Length头部
        ss << "Content-Length: " << body.length() << "\r\n";
        //添加其它头部字段
        for (const auto& header : headers) {
            ss << header.first << ": " << header.second << "\r\n";
        }
        
        ss << "\r\n";//添加空行分割头部和消息体
        ss << body;//消息体
        
        return ss.str();
    }
    std::string HttpResponse::getStatusText(int status_code) const{
        switch (status_code) {
            case 200: return "OK";//请求成功
            case 201: return "Created";//请求成功并且资源被创建
            case 302: return "Found"; // 请求的资源临时移动到新位置
            case 400: return "Bad Request";// 客户端请求的语法错误，服务器无法理解
            case 401: return "Unauthorized";// 请求要求用户的身份认证
            case 403: return "Forbidden";// 服务器理解请求，但拒绝执行
            case 404: return "Not Found";// 服务器无法找到请求的资源
            case 409: return "Conflict";// 请求与服务器的当前状态冲突
            case 500: return "Internal Server Error"; // 服务器内部错误
            default: return "Unknown";// 未知状态码
        }
    }
}