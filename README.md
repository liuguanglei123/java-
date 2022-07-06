# java-
java基础，你真的了解吗？

#### sleep和wait
sleep和wait的示例在SleepAndWait.java文件中
Thread.sleep(long millis):

Thread类下的一个静态方法，可以用来作为休眠进程的方法使用，

wait()

wait(long timeout)

属于Object类对象的方法，不是静态方法，所以必须要有一个调用的对象。

使用场景：

当某个线程获取到锁后，发现当前还不满足执行的条件，就可以调用对象锁的wait方法，进入等待状态。
直到某个时刻，外在条件满足了，就可以由其他线程通过调用该对象的notify()或者notifyAll()方法，来唤醒先前进入等待状态的线程。


这篇文章将侧重于讨论wait()方法对于线程状态的影响，以及被唤醒后线程的状态变更。
————————————————
版权声明：本文为CSDN博主「小楼夜听雨QAQ」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_37855749/article/details/117073990

基础的几种集合 list set map treeset treemap
基础的字符串类 string stringbuilder stringbuffer
复杂一点的数据结构 二叉树 红黑树 B树
git基础
时间日期类
bigdecimal与interger类
IO流
位运算
运算符重载
Cumsume Function 等区别
匿名内部类
可变数量的形参
javaBean

锁 自旋锁
wait和notify
hashmap hashtable

properties集合
多线程 继承thread runnable callable 如何启动一个新的线程 
Lock接口 实现类 ReentrantLock
juc java utils concurrent并发

线程通信方法 wati() notify()
sleep与wait

脏读幻读不可重复读

什么是servlet