# 指定CMake的最低版本要求
cmake_minimum_required(VERSION 3.7) # 确保使用的CMake版本支持所需功能

# 定义项目名称和语言
project(cpp_web_server LANGUAGES CXX) # 定义项目名称为cpp_web_server，使用C++语言

# 设置C++标准
set(CMAKE_CXX_STANDARD 17) # 使用C++17标准
set(CMAKE_CXX_STANDARD_REQUIRED ON) # 强制要求使用指定的C++标准
set(CMAKE_CXX_EXTENSIONS OFF) # 禁用编译器特定的扩展，确保代码的可移植性

# 添加编译选项
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra") # 启用所有警告和额外警告
set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -g -O0") # 调试模式下启用调试符号和禁用优化

# 设置构建类型
set(CMAKE_BUILD_TYPE Debug) # 默认构建类型为Debug，便于调试

# 查找线程库
find_package(Threads REQUIRED) # 查找并链接线程库，确保多线程支持

# 添加子目录
add_subdirectory(src/http) # 添加src/http子目录，递归处理其CMakeLists.txt文件

# 设置输出目录
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/bin) # 将生成的可执行文件输出到bin目录

# 安装目标
install(TARGETS cpp_web_server DESTINATION bin) # 安装可执行文件到bin目录


