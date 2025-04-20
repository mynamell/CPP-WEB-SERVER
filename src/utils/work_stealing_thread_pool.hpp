#pragma once
#include <vector>
#include <deque>
#include <thread>
#include <mutex>
#include <atomic>
#include <memory>
#include <condition_variable>
#include <functional>
#include <future>
#include <random>

namespace utils {

// 一个工作窃取线程池类，用于管理多个线程并支持任务窃取以提高并发性能。
class WorkStealingThreadPool {
public:
    // 构造函数，初始化线程池并启动指定数量的线程。
    explicit WorkStealingThreadPool(size_t numThreads);

    // 析构函数，确保线程池正确关闭并释放资源。
    ~WorkStealingThreadPool();

    // 将任务加入线程池队列并返回一个 future 对象，用于获取任务结果。
    template<typename F>
    auto enqueue(F&& f) -> std::future<typename std::invoke_result<F>::type>;

private:
    // 表示一个任务队列，包含任务列表、互斥锁和条件变量。
    struct TaskQueue {
        std::deque<std::function<void()>> tasks; // 任务队列
        std::mutex mutex;                        // 保护任务队列的互斥锁
        std::condition_variable condition;       // 用于任务通知的条件变量
    };

    std::vector<std::thread> workers;                  // 工作线程列表
    std::vector<std::unique_ptr<TaskQueue>> local_queues; // 每个线程对应的本地任务队列
    std::atomic<bool> stop;                            // 标志线程池是否停止
    std::atomic<size_t> next_queue_index{0};           // 用于循环分配任务队列的索引

    // 尝试从其他队列窃取任务。
    bool steal_task(std::function<void()>& task, size_t current_index);

    // 获取一个随机的队列索引，用于任务窃取。
    size_t get_random_queue_index(size_t max_index, size_t current_index);
};

// 将任务加入线程池队列并返回一个 future 对象，用于获取任务结果。
template<class F, class... Args>
auto WorkStealingThreadPool::enqueue(F&& f, Args&&... args) -> std::future<typename std::invoke_result<F, Args...>::type> {
    using return_type = typename std::invoke_result<F, Args...>::type;

    // 创建一个打包任务，将函数和参数绑定到一起。
    auto task = std::make_shared<std::packaged_task<return_type()>>(
        std::bind(std::forward<F>(f), std::forward<Args>(args)...)
    );

    auto res = task->get_future(); // 获取任务的 future 对象
    size_t queue_index = next_queue_index++ % local_queues.size(); // 选择一个任务队列
    {
        auto& queue = local_queues[queue_index];
        std::unique_lock<std::mutex> lock(queue->mutex); // 加锁保护任务队列
        queue->tasks.emplace_back([task]() { (*task)(); }); // 将任务加入队列
    }
    local_queues[queue_index]->condition.notify_one(); // 通知队列有新任务
    return res;
}

}