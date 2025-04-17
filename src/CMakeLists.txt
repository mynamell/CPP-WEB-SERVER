# 生成可执行文件
# 定义目标可执行文件 http_server，并添加其源文件
add_executable(http_server
    http_server.cpp       # HTTP服务器实现
    http_request.cpp      # HTTP请求解析实现
    http_response.cpp     # HTTP响应生成实现
    main.cpp              # 主程序入口
    utils/logger.cpp      # 日志模块实现
)

# 链接库
# 将线程库链接到目标 http_server
target_link_libraries(http_server
    PRIVATE              # 使用私有链接，确保依赖仅对当前目标可见
    Threads::Threads     # 链接线程库，支持多线程操作
)

# 设置二进制输出目录
# 指定生成的可执行文件的输出路径
set_target_properties(http_server PROPERTIES
    RUNTIME_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/bin" # 输出到构建目录的 bin 文件夹
)

# 安装目标
# 定义安装规则，将生成的可执行文件安装到系统的 bin 目录
install(TARGETS http_server DESTINATION bin)

# 添加编译选项（可选）
# 启用所有警告和额外警告，便于发现潜在问题
target_compile_options(http_server PRIVATE -Wall -Wextra)

# 添加调试信息（可选）
# 在调试模式下启用调试符号，便于调试
if(CMAKE_BUILD_TYPE STREQUAL "Debug")
    target_compile_options(http_server PRIVATE -g)
endif()