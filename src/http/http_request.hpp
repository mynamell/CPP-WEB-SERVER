#pragma once
#include <string>
#include <unordered_map>

namespace http {

class HttpRequest {
public:
    std::string method;//http方法
    std::string path;//请求路径
    std::string body;//请求体
    std::unordered_map<std::string,std::string> headers;//请求头
    std::unordered_map<std::string,std::string> query_params;//请求参数
    HttpRequest() = default;
    // POST /login?username=test%20user&password=123456 HTTP/1.1
    // Host: www.example.com
    // Content-Type: application/x-www-form-urlencoded
    // Content-Length: 21
    // username=admin&token=abc

    // method = "POST"
    // path = "/login"
    //body username=admin&token=abc
    //headers {"Host", "www.example.com"},
    // {"Content-Type", "application/x-www-form-urlencoded"},
    //{"Content-Length", "21"}
    //解析URL中的查询函数
    void parse_query_params(){
        size_t pos=path.find('?');
        if(pos!=std::string::npos){
            std::string query_string=path.substr(pos+1);
            path=path.substr(0,pos);
            size_t start=0;
            size_t end=0;
            while((end=query_string.find('&',start))!=std::string::npos){
                parseParam(query_string.substr(start,end-start));
                start=end+1;
            }
            parseParam(query_string.substr(start));
        }
    }
private:
    void parseParam(const std::string& param){
        size_t equals_pos=param.find('=');
        if(equals_pos!=std::string::npos){
            std::string key=param.substr(0,equals_pos);
            std::string value=param.substr(equals_pos+1);
            query_params[urlDecode(key)]=urlDecode(value);
        }
    }

    //解码前search?q=%E6%B8%B8%E6%88%8F+%E5%BC%80%E5%8F%91
    //解码后search?q=游戏 开发
    std::string urlDecode(const std::string& encoded){
        std::string decoded;
        for(size_t i=0;i<encoded.Length();i++){
            if(encoded[i]=='%' && i+2<encoded.Length()){
                int value;
                std::sscanf(encoded.substr(i+1,2).c_str(),"%x",&value);
                decoded+=static_cast<char>(value);
                i+=2;
            }else if(encoded[i]=='+'){
                decoded+=' ';

            }else{
                decoded+=encoded[i];
            }
        }
        return decoded;
    }


};

}