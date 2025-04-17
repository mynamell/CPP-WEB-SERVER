#pragma once
#include <iostream> // 用于输入输出（调试时可能用到）
#include <vector>   // 用于存储线程的容器
#include <queue>    // 用于任务队列
#include <mutex>    // 用于线程间的互斥锁
#include <thread>   // 用于创建和管理线程
#include <future>   // 用于异步任务的返回值
#include <functional> // 用于存储可调用对象（如函数、lambda）

namespace utils { // 定义 utils 命名空间，避免命名冲突

class ThreadPool { // 定义线程池类
public:
    explicit ThreadPool(size_t num_thread); // 构造函数，初始化线程池，指定线程数量
    ~ThreadPool(); // 析构函数，销毁线程池，释放资源

    // 模板方法，用于向线程池中添加任务
    template<class F, class... Args>
    auto enqueue(F&& f, Args&&... args)
        -> std::future<typename std::invoke_result<F, Args...>::type>; // 返回任务的 future 对象，用于获取任务结果

private:
    std::vector<std::thread> workers; // 线程池中的工作线程
    std::queue<std::function<void()>> tasks; // 任务队列，存储待执行的任务
    std::mutex queue_mutex; // 互斥锁，用于保护任务队列的访问
    std::condition_variable condition; // 条件变量，用于线程间的同步
    bool stop; // 停止标志，指示线程池是否停止工作
};

// 构造函数，初始化线程池
template<class F, class... Args>
auto ThreadPool::enqueue(F&& f, Args&&... args)
    -> std::future<typename std::invoke_result<F, Args...>::type> { // 返回任务的 future 对象
        using return_type = typename std::invoke_result<F, Args...>::type; // 获取任务的返回类型

        // 创建一个封装任务的 shared_ptr
        auto task = std::make_shared<std::packaged_task<return_type()>>(
            std::bind(std::forward<F>(f), std::forward<Args>(args)...) // 使用 std::bind 绑定任务和参数
        );

        std::future<return_type> res = task->get_future(); // 获取任务的 future 对象，用于获取结果
        {
            std::unique_lock<std::mutex> lock(queue_mutex); // 加锁，保护任务队列
            if (stop) { // 如果线程池已经停止，抛出异常
                throw std::runtime_error("enqueue on stopped ThreadPool");
            }
            tasks.emplace([task]() { (*task)(); }); // 将任务添加到任务队列中
        }
        condition.notify_one(); // 通知一个等待的线程，任务队列中有新任务
        return res; // 返回任务的 future 对象
    }

} // namespace utils