#include "http_request.hpp"
#include <sstream>
#include <vector>
#include <iostream>

namespace http {

    HttpRequest::HttpRequest::parse(const std::string& request_str){
        HttpRequest request;
        std::istringstream request_stream(request_str);
        std::string line;
        // 解析请求行
        std::getline(request_stream, line);
        if(!line.empty()&&line.back()=='\r'){
            line.pop_back();
        }
        std::istringstream request_line(line);
        request_line >> request.method>>request.path;
        //解析查询参数
        request.parse_query_params();

        while(std::getline(request_stream, line)&&line!="\r"&&line!=""){
            if(!line.empty()&&line.back()=='\r'){
                line.pop_back();
            }
            size_t colon_pos= line.find(':');//冒号位置
            if(colon_pos!=std::string::npos){
                std::string key=line.substr(0,colon_pos);
                std::string value=line.substr(colon_pos+2);
                if(!value.empty()&&value[0]=='\r'){
                    value=value.substr(1);
                }
                request.headers[key]=value;
            }

        }
        // 解析请求体
        auto content_length_it=request.headers.find("Content-Length");
        if(content_length_it!=request.headers.end()){
            size_t content_length=std::stoul(content_length_it->second);
            while(std::getline(request_stream, line)&&(line=="\r"||line=="")){
                continue;
            }
        }

        request.body=line;
        if(!request.body.empty()&&request.body.back()=='\r'){
            request.body.pop_back();
        }

        if(request.body.length()<content_length){
            std::vector<char> remaining_buffer( content_length-request.body.length());
            request_stream.read(remaining_buffer.data(),remaining_buffer.size());
            request.body+=std::string(remaining_buffer.data(),request_stream.gcount());
        }
        LOG_DEBUG << "Read body with length " << request.body.length() 
        << " (expected " << content_length << ")";
    }
    return request;
}