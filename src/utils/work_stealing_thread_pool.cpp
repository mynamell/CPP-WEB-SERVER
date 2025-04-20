#include "work_stealing_thread_pool.hpp"
#include <random>

namespace utils{

WorkStealingThreadPool::WorkStealingThreadPool(size_t numThreads) : stop(false) {
    
   //初试化线程池
   WorkStealingThreadPool::work_stealing_thread_pool(size_t num_threads)
   :stop(false)
   {
    local_queues.resize(num_threads);
    for(size_t i = 0; i < num_threads; ++i) {
        local_queues[i] = std::make_unique<TaskQueue>();//为每个线程分配一个任务队列
    }
    //创建工作线程
    for(size_t i=0;i<num_threads;++i){
        workers.emplace_back([this,i]{
           while(true){
                std::function<void()> task;//存储当前要执行得任务
                bool has_task = false;//标记是否找到任务
                //尝试从本地队列获取任务
                {
                    auto& local_queue = local_queues[i]; // 获取当前线程的本地队列
                    std::unique_lock<std::mutex> lock(local_queue->mutex); // 加锁以访问队列
                    if (!local_queue->tasks.empty()) { // 如果队列不为空
                        task = std::move(local_queue->tasks.front()); // 获取队列中的第一个任务
                        local_queue->tasks.pop_front(); // 从队列中移除任务
                        has_task = true; // 标记找到任务
                    }
                }
                //如果本地队列没有任务，尝试从其他队列窃取任务
                if(!has_task){
                    if(steal_task(task,i)){// 调用窃取任务的函数
                        has_task = true; //如果窃取成功，标记找到任务
                    }else{
                        //如果没有任务，等待其他线程通知
                        auto& local_queue = local_queues[i];
                        std::unique_lock<std::mutex> lock(local_queue->mutex);
                        local_queue->condition.wait(lock, [this, &local_queue] {
                             // 等待条件变量通知，或者本地队列有新任务
                             return stop || !local_queue->tasks.empty();
                        });
                        if(stop && local_queue->tasks.empty()){
                            return; // 如果线程池停止且本地队列为空，退出线程
                        }
                        //如果本地队列有任务，重新获取任务
                        if(!local_queue->tasks.empty()){
                            task = std::move(local_queue->tasks.front());
                            local_queue->tasks.pop_front();
                            has_task = true;
                        }

                    }

                }
                if(has_task){
                    task(); // 执行任务
                }

           }
        });
    }

   }
   //析构函数，确保线程池正确关闭并释放资源
   WorkStealingThreadPool::~WorkStealingThreadPool() {
       stop = true; // 设置停止标志
       for(auto& queue:local_queue){
              queue->condition.notify_all(); // 通知所有线程
       }
       for(auto& worker : workers) {
           worker.join(); // 等待所有线程完成
       }
   }
   //窃取任务得函数
   bool WorkStealingThreadPool::steal_task(){
        size_t num_queues=local_queues.size();//获取队列数量
        size_t start_index=get_random_queue_index(num_queues, current_index);//获取随机队列索引
        //尝试从其它队列窃取任务
        for(size_t i=0;i<num_queues;++i){
            size_t index=(start_index+i)%num_queues;//循环获取队列索引
            if(index==current_index) continue; //跳过当前线程的队列
            auto& queue=local_queues[index];
            std::unique_lock<std::mutex> lock(queue->mutex, std::try_to_lock); // 尝试加锁
            if (lock && !queue->tasks.empty()) { // 如果成功加锁且队列不为空
                task = std::move(queue->tasks.front()); // 获取队列中的第一个任务
                queue->tasks.pop_front(); // 从队列中移除任务
                return true; // 窃取成功
            }
        }
        return false; // 窃取失败
   }
    //获取随机队列索引
    size_t WorkStealingThreadPool::get_random_queue_index(size_t max_index,size_t current_index)
    {
        static thread_local<std::mt19937> rng(std::random_device{}()); // 使用随机数生成器
        std::uniform_int_distribution<size_t> dist(0, max_index - 1); // 定义均匀分布
        size_t index;
        do{
            index = dist(rng); // 生成随机索引
        }while(index==current_index&&max_index>1); // 确保不等于当前索引
        return index; // 返回随机索引
    }

}