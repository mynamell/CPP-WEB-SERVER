// #include <iostream>
// #include "chat_application.hpp"
// #include "utils/logger.hpp"

// int main() {
//     try {
//         utils::Logger::init();
//         // Use port 8080 for both HTTP and WebSocket
//         LOG_INFO << "Chat server starting...";

//         ChatApplication app("static");
//         LOG_INFO << "Server listening on port 8080";
//         app.start(8080);
//     } catch (const std::exception& e) {
//         LOG_ERROR << "Error: " << e.what();
//         return 1;
//     }
//     return 0;
// }
#include <iostream>
#include "chat_application.hpp"
#include "utils/logger.hpp"

constexpr int SERVER_PORT = 8080; // 定义端口号常量

void runServer() {
    utils::Logger::init();
    LOG_INFO << "Chat server starting...";

    ChatApplication app("static");
    LOG_INFO << "Server listening on port " << SERVER_PORT;
    app.start(SERVER_PORT);
}

int main() {
    try {
        runServer();
    } catch (const std::exception& e) {
        LOG_ERROR << "Error: " << e.what();
        return 1;
    } catch (...) {
        LOG_ERROR << "Unknown error occurred.";
        return 1;
    }
    return 0;
}