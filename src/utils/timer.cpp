#include "timer.hpp"

namespace utils {
    Timer::Timer() : running_(false) {}
    Timer::~Timer() {
        stop(); // 停止定时器
    }
    //添加一次性定时任务
    void Timer::addOnceTask(std::chrono::milliseconds delay, std::function<void()> callback) {
        std::lock_guard<std::mutex> lock(mutex_); // 加锁
        auto execution_time = std::chrono::steady_clock::now() + delay; // 计算执行时间
        Task task(execution_time,std::move(callback)); // 添加任务
        once_tasks_.push(std::move(task)); // 将任务添加到队列
        cv_.notify_one(); // 通知定时器线程

    }
    void Timer::start(){
        std::lock_guard<std::mutex> lock(mutex_); // 加锁
        if(!running_){
            running_=true; // 设置定时器为运行状态
            timer_thread_=std::thread([this]{processTimerTasks();}); // 启动定时器线程
        }
    }
    void timer::stop(){
        {
            std::lock_guard<std::mutex> lock(mutex_); // 加锁
            running_=false; // 设置定时器为停止状态
            cv_.notify_one(); // 通知定时器线程
        }
        if(timer_thread_.joinable()){
            timer_thread_.join(); // 等待定时器线程结束
        }
    }
    void Timer::processTimerTasks(){
        while(true){
            std::unique_lock<std::mutex> lock(mutex_); // 加锁
         
            if(!running_){
                break; // 如果定时器停止，退出循环
            }
            if(once_tasks_.empty()&&periodic_tasks_.empty()){
                cv_.wait(lock,[this]{return !running_ ||!once_tasks_.empty()||
                !periodic_tasks_.empty();}); // 等待任务
                continue; // 继续循环
                }
            auto now=std::chrono::steady_clock::now(); // 获取当前时间
            Task* earliest_task=nullptr; // 初始化最早任务指针
            auto earliest_time=std::chrono::steady_clock::time_point::max(); // 初始化最早时间
            //检查一次性任务队列
            if(!once_tasks_.empty()){
                Task& task=once_tasks_.front(); // 获取队列头部任务
                if(task.execution_time<earliest_time){
                    earliest_time=task.execution_time; // 更新最早时间
                    earliest_task=new Task(std::move(task)); // 更新最早任务
                    is_from_periodic=false; // 标记为一次性任务
                }
            }
            //检查周期性任务队列
            if(!periodic_tasks_.empty()){
                Task& task=periodic_tasks_.front(); // 获取队列头部任务
                if(task.execution_time<earliest_time){
                   if(earliest_task!=nullptr)
                    {
                        delete earliest_task; // 删除最早任务
                    }
                    earliest_time=task.execution_time; // 更新最早时间
                    earliest_task=new Task(std::move(task)); // 更新最早任务
                    is_from_periodic=true; // 标记为周期性任务
                }
            }
            //如果最早的任务还没到达执行时间，等戴
            if(earliest_task && earliest_task->execution_time>now){
                if(is_from_periodic){
                    periodic_tasks_.front()=std::move(*earliest_task); // 更新周期性任务
                }else{
                    once_tasks_.front()=std::move(*earliest_task); // 更新一次性任务
                }
                delete earliest_task; // 删除最早任务
                cv_.wait_until(lock,execution_time); // 等待任务到达执行时间
                continue; // 继续循环
            }
            //执行最早的任务
            if(earliest_task){
                Task task=std::move(*earliest_task); // 移动任务
                delete earliest_task; // 删除最早任务
                //从对应队列中移除任务
                if(is_from_periodic){
                    periodic_tasks_.pop(); // 移除周期性任务
                    task.execution_time+=task.period; // 更新执行时间
                    periodic_tasks_.push(std::move(task)); // 添加到周期性任务队列
                }else{
                    once_tasks_.pop(); // 移除一次性任务
                }
                lock.unlock(); // 解锁
                task.callback(); // 执行任务
            }
        }

    }
}