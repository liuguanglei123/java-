# java-
java基础，你真的了解吗？

#### sleep和wait
sleep和wait的示例在SleepAndWait.java文件中

##### sleep方法
Thread类下的一个静态方法，可以用来作为休眠进程的方法使用

```
Thread.sleep(4000);
```

##### wait方法
属于Object类对象的方法，不是静态方法，所以必须要有一个调用的对象。

使用场景：
调用wait后，使得当前线程立刻停止运行，处于等待状态（WAIT），并将当前线程置入锁对象的等待队列中，直到被通知（notify）或被中断为止。

使用条件：wait方法只能在同步方法或同步代码块中使用，而且必须是内建锁。wait方法调用后立刻释放对象锁

wait方法的重载
```
1) public final void wait() throws InterruptedException——一直等待，直到被唤醒或中断
2) public final native void wait(long timeout) throws InterruptedException——超时等待：若在规定时间内未被唤醒，则线程退出，单位：毫秒
3) public final void wait(long timeout, int nanos) throws InterruptedException——在2的基础上增加了纳秒控制
```
wait方法一般不会单独使用，需要配合notify
##### notify方法
调用后，将唤醒处于等待状态的线程

使用条件：notify（）也必须在同步方法或同步代码块中调用，用来唤醒等待该对象的其他线程。
如果有多个线程在等待，随机挑选一个线程唤醒（唤醒哪个线程由JDK版本决定）。
notify方法调用后，当前线程不会立刻释放对象锁，要等到当前线程执行完毕后再释放锁。

notify的另一种使用方法：notifyAll()

唤醒所有处于等待状态的线程

延伸的一个小知识点：

每个锁都有两个队列，一个是同步队列，存储获取锁失败的进程；另一个是等待队列，存储的是调用wait()等待状态的线程，
将线程唤醒，实际上就是将处理等待队列的线程移到同步队列中的过程，此后所有的线程再去竞争锁。

##### wait和sleep的区别
* wait是非静态的，sleep是静态的
* wait需要被唤醒，sleep不需要
* 最大的区别：
    * sleep在休眠的过程中，同步锁不会丢失，不释放（jdk的官方文档是：不丢失任何监视器的所属权）
    * wati在等待的时候，发布监视器的所属权，将锁释放，这样其他线程就可以去竞争锁。
    当其他线程通过notify方法通知后唤醒先前的线程后，线程需要重新获得对监视器的所有权（对象锁）后才能够继续执行！
    
#### 再拓展：notifyAll导致频繁与OS底层交互，导致性能低怎么办？
由于notifyAll方法会调用OS恢复线程，然而很多时候其实只需要一个线程启动工作就可以，其他线程继续等待，
这个时候我们可以使用Lock接口，来替换Synchronized，提供更好的加锁操作
* Lock接口中的newCondition()方法，可以创建指定的集合容器，用于线程等待

常用的Lock接口的实现类是 ReentrantLock
    
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

脏读幻读不可重复读

什么是servlet