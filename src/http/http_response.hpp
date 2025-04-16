#pragma
#include <string>
#include <unordered_map>

namespace http{

    class HttpRequest{
        public:
            int status_code;//状态码
            std::string body;//响应体
            std::unordered_map<std::string,std::string> headers;//响应头
            HttpResponse(int code=200,const std::string& body="");
            std::string to_string() const;//将响应转换为字符串
        private:
            static std::string getStatusText(int code);//获取状态码对应的文本描述
        };
}