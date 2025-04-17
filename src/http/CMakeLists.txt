# 生成可执行文件
add_executable(http_server
    http_server.cpp
    http_server.hpp
    http_request.cpp
    http_request.hpp
    http_response.cpp
    http_response.hpp
    main.cpp
)

# 链接库
target_link_libraries(http_server
    PRIVATE
    Threads::Threads
)

# 设置二进制输出目录（绝对路径）
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_BINARY_DIR}/bin)

# 或者使用现代CMake语法（推荐）
set_target_properties(http_server PROPERTIES
    RUNTIME_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/bin"
)

# 确保install指令存在
install(TARGETS http_server DESTINATION bin)