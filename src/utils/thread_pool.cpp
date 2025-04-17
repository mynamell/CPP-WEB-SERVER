#include "src/utils/ thread_pool.hpp"

namespace utils{

ThreadPool::ThreadPool(size_t num_threads):stop(false){

    for(size_t i=0;i<num_threads;++i){
        workers.emplace_back([this]{
            while(true)
            {
                std::function<void()> task;
                {
                    std::unique_lock<std::mutex> lock(this->queue_mutex);
                    this->condition.wait(lock,[this]{return this->stop||!this->tasks.empty();});
                    if(this->stop&&this->tasks.empty()){
                        return;
                    }
                    task=std::move(this->tasks.front());
                    this->tasks.pop();
                }
                task(); // 执行任务

            }
        });

    }

}
ThreadPool::~ThreadPool(){
    {
        std::unique_lock<std::mutex> lock(queue_mutex);
        stop=true;
    }
    condition.notify_all(); // 通知所有线程
    for(std::thread& worker:workers){
        worker.join(); // 等待所有线程完成
    }
}

}