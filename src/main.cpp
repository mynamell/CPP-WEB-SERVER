#include <iostream>
#include "http_server.hpp"

int main() {
    HttpServer server(8080);
    server.start();
    return 0;
}