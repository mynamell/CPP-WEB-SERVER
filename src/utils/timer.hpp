


namespace utils {

class Timer{
public:
    struct Task{
        std::chrono::steady_clock::time_point execution_time; // 任务执行时间
        std::function<void()> callback; // 任务回调函数
        bool is_periodic; // 是否为周期性任务
        std::chrono::milliseconds period; // 周期性任务的间隔时间
        Task(std::chrono::steady_clock::time_point time, 
            std::function<void()> cb, 
            bool periodic = false, 
            std::chrono::milliseconds period = std::chrono::milliseconds(0))
            : execution_time(time), callback(cb), is_periodic(periodic), period(period) {}
        bool operator>(const Task& other) const {
            return execution_time > other.execution_time; // 比较任务的执行时间
        }

    };
    explicit Timer();
    ~Timer();
    //添加一次性定时任务
    void addOnceTask(std::chrono::milliseconds delay, std::function<void()> callback);
    void addPeriodicTask(std::chrono::milliseconds delay, 
        std::chrono::milliseconds period, 
        std::function<void()> callback);
        //启动定时器
        void start();
        //停止定时器
        void stop();
private:
    void processTimerTasks(); // 处理定时任务
    void scheduleNextTask(); // 安排下一个任务

    std::queue<Task> once_tasks_; // 一次性任务队列
    std::queue<Task> periodic_tasks_; // 周期性任务队列
    std::mutex mutex_; // 互斥锁，用于保护任务队列
    std::condition_variable cv_; // 条件变量，用于线程间的同步
    std::thread timer_thread_; // 工作线程
    bool running_; // 标志，指示定时器是否在运行
};


}