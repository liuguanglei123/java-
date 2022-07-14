## sleep和wait
sleep和wait的示例在SleepAndWait.java文件中

### sleep方法
Thread类下的一个静态方法，可以用来作为休眠进程的方法使用

```
Thread.sleep(4000);
```

### wait方法
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

### notify方法
调用后，将唤醒处于等待状态的线程

使用条件：notify（）也必须在同步方法或同步代码块中调用，用来唤醒等待该对象的其他线程。
如果有多个线程在等待，随机挑选一个线程唤醒（唤醒哪个线程由JDK版本决定）。
notify方法调用后，当前线程不会立刻释放对象锁，要等到当前线程执行完毕后再释放锁。

notify的另一种使用方法：notifyAll()

唤醒所有处于等待状态的线程

延伸的一个小知识点：

每个锁都有两个队列，一个是同步队列，存储获取锁失败的进程；另一个是等待队列，存储的是调用wait()等待状态的线程，
将线程唤醒，实际上就是将处理等待队列的线程移到同步队列中的过程，此后所有的线程再去竞争锁。

### wait和sleep的区别
* wait是非静态的，sleep是静态的
* wait需要被唤醒，sleep不需要
* 最大的区别：
    * sleep在休眠的过程中，同步锁不会丢失，不释放（jdk的官方文档是：不丢失任何监视器的所属权）
    * wati在等待的时候，发布监视器的所属权，将锁释放，这样其他线程就可以去竞争锁。
    当其他线程通过notify方法通知后唤醒先前的线程后，线程需要重新获得对监视器的所有权（对象锁）后才能够继续执行！
    
### 再拓展：notifyAll导致频繁与OS底层交互，导致性能低怎么办？
由于notifyAll方法会调用OS恢复线程，然而很多时候其实只需要一个线程启动工作就可以，其他线程继续等待，
这个时候我们可以使用Lock接口，来替换Synchronized，提供更好的加锁操作
* Lock接口中的newCondition()方法，可以创建指定的集合容器，用于线程等待

常用的Lock接口的实现类是 ReentrantLock，代码实例请查看ReentrantLockTest.java

Lock接口的特点：
* 可以生成Condition接口（实为线程的阻塞队列）
    * 进入队列的线程，释放锁
    * 出去队列的线程，再次获得锁
    * 接口的方法：await()线程释放锁，然后进入队列
    * 接口的方法：signal()线程出队列，再次获取锁
> 线程的阻塞队列，依赖lock接口创建，如ReentrantLockTest.java中的
```
lock.lock();//获取锁
```
> 再深入一点，Lock接口使用的锁的原理是什么？为什么能保证一致性？这就需要引入更复杂的锁的概念
## 锁

### 悲观锁与乐观锁
乐观锁和悲观锁是两种思想，主要是解决并发场景下的数据争夺的问题。

乐观锁：乐观锁在操作数据时非常乐观，认为别人不会同时修改数据。因此乐观锁不会上锁，
只是在执行更新的时候判断一下在此期间别人是否修改了数据：如果别人修改了数据则放弃操作，
否则执行操作。

悲观锁：悲观锁在操作数据时比较悲观，认为别人会同时修改数据。因此操作数据时直接把数据锁住，
直到操作完成后才会释放锁；上锁期间其他人不能修改数据。

### 锁的实现方式
#### 悲观锁的实现
synchronized是最基础的悲观锁的实现方式，当对某些对象进行加锁时，会与OS交互，拒绝访问，
需要等到当前处理完成之后才其他线程才能继续

常见的分布式锁也常用悲观锁，在分布式系统中，当多个服务访问同一资源时，可能会在redis中加入一个锁标记，
只有获取到该锁标记的服务才能使用该资源

### 乐观锁的实现
上面提到的Lock接口的锁的实现类ReentrantLock，就是一种乐观锁，也称为CAS(Compare and Swap)/自旋锁

CAS的原理很简单，包含三个值

* 需要读写的内存位置(V)
* 预期原来的值(A)
* 期待更新的值(B)

CAS操作逻辑如下：
> 如果内存位置V的值等于预期的A值，则将该位置更新为新值B，否则不进行更新操作。
许多CAS的操作是自旋的：如果操作不成功，会一直重试，直到操作成功为止。

这里引出一个新的问题，既然CAS包含了Compare和Swap两个操作，它又如何保证原子性呢？

答案是：CAS是由CPU支持的原子操作，其原子性是在硬件层面进行保证的。

要实现这个需求，java中提供了Unsafe类，它提供了三个函数，分别用来操作基本类型int和long，以及引用类型Object。如果比较发现值已经被修改了，方法会返回false。
![unsafe类](https://github.com/liuguanglei123/java-base/blob/main/images/unsafe.png)

常用的atomic类，比如AtomicInteger AtomicLong，就是使用了上面的方法，来保证一致性的，如图
![unsafe类](https://github.com/liuguanglei123/java-base/blob/main/images/atomicInteger.png)
![unsafe类](https://github.com/liuguanglei123/java-base/blob/main/images/atomicInteger2.png)


#### CAS的ABA问题
如果一个线程t1正修改共享变量的值A，但还没修改，此时另一个线程t2获取到CPU时间片，将共享变量的值A修改为B，
然后又修改为A，此时线程t1检查发现共享变量的值没有发生变化，但是实际上却变化了，尤其是当A为一个复杂对象时，其内部的值可能已经变了。

解决办法：使用版本号方法解决，在变量前面追加上版本号，每次变量更新的时候把版本号加1，那么A－B－A 就会变成1A-2B-3A。从Java1.5开始JUC包里提供了一个类AtomicStampedReference来解决ABA问题。
AtomicStampedReference类的compareAndSet方法作用是首先检查当前引用是否等于预期引用，并且当前版本号是否等于预期版本号，
如果全部相等，则以原子方式将该引用和该标志的值设置为给定的更新值。
















