# 线程池
java中经常会用到多线程来处理一些任务，如果线程数量比较多的情况下，不建议直接使用继承Thread类或者实现Runnable接口的方式
来创建线程，那样一定会产生创建及销毁线程耗费资源，及线程切换上下文问题，同时创建过多的线程也会引发资源耗尽的风险，这个时候我们就
需要引入线程池。Java中涉及到线程池的内容在jdk1.5版本后的java.util.concurrent包中，涉及到的核心类和接口大概有：
Executor、Executors、ExecutorService、ThreadPoolExecutor、FutureTask、Callable、Runnable等

## Executors类
* 静态方法 public static ExecutorService newFixedThreadPool(int nthreads)
    * 方法的返回值 ExecutorsService接口的实现类，管理线程池里面的线程
* ExecutorService接口的方法
    * submit(Runnable r) 提交线程执行的任务

