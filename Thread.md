# 线程
Java中涉及到线程的内容在jdk1.5版本后的java.util.concurrent包中，涉及到的核心类和接口大概有：
Executor、Executors、ExecutorService、ThreadPoolExecutor、FutureTask、Callable、Runnable等

## 创建一个线程的多种方式
### Thread类
```
    public class MyThread extends Thread {

        @Override
        public void run() {
            while (true) {
                System.out.println("this is thread!");
            }
        }
    }

```
### Runnable接口
```
    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("this is runnable");
            }
        }
    }
```

Thread类和Runnable接口使用方法基本类似，都是定以一个新的类对象，然后通过new Thread().start来执行，区别是
```
    //Thread对象通过如下方法调用
    new MyThread().start;
    //Runnable对象通过如下方法调用
    new Thread(MyRunnable).start();
```
### Callable接口
返回结果并且可能抛出异常的任务。
优点：
* 可以获得任务执行返回值；
* 通过与Future的结合，可以实现利用Future来跟踪异步计算的结果。

#### Runnable和Callable的区别：
1) Callable覆写的方法是call(),Runnable覆写的方法是run().
2) Callable的任务执行后可返回值，而Runnable的任务是不能返回值的
3) call方法可以抛出异常，run方法不可以
4) 运行Callable任务可以拿到一个Future对象，表示异步计算的结果。它提供了检查计算是否完成的方法，
以等待计算的完成，并检索计算的结果。通过Future对象可以了解任务执行情况，可取消任务的执行，还可获取执行结果。

### Future接口
* Future是一个接口，代表了一个异步计算的结果。接口中的方法用来检查计算是否完成、等待完成和得到计算的结果。
* 当计算完成后，只能通过get()方法得到结果，get方法会阻塞直到结果准备好了。
* 如果想取消，那么调用cancel()方法。其他方法用于确定任务是正常完成还是取消了。
* 一旦计算完成了，那么这个计算就不能被取消。

### FutureTask类
* FutureTask类实现了RunnableFuture接口，而RunnnableFuture接口继承了Runnable和Future接口，所以说FutureTask是一个提供异步计算的结果的任务。
* FutureTask可以用来包装Callable或者Runnbale对象。因为FutureTask实现了Runnable接口，所以FutureTask也可以被提交给Executor（如上面例子那样）。

## Callable两种执行方式
完整的代码查看ThreadTest1.java
1) 借助FutureTask执行

FutureTask类同时实现了两个接口，Future和Runnable接口，所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值。
具体流程：

```
    //定义实现Callable接口的的实现类重写call方法。
  public class MyCallableTask implements Callable<Integer>{
      @Override
          public Integer call() throws Exception {
             //TODO 线程执行方法
          }
  }
  ---------------------------------------------------------
  //创建Callable对象
  Callable<Integer> mycallabletask = new MyCallableTask();
  //开始线程
  FutureTask<Integer> futuretask= new FutureTask<Integer>(mycallabletask);
  new Thread(futuretask).start();
  --------------------------------------------------------
  通过futuretask可以得到MyCallableTask的call()的运行结果：
  futuretask.get();
```

2) 借助线程池来运行
线程池中执行Callable任务原型：
```
    public interface ExecutorService extends Executor {
        //提交一个Callable任务，返回值为一个Future类型
        <T> Future<T> submit(Callable<T> task);

        //other methods...
    }
    //借助线程池来运行Callable任务的一般流程为：
    ExecutorService exec = Executors.newCachedThreadPool();
    Future<Integer> future = exec.submit(new MyCallableTask());
```

## Thread的join方法
join简介

join方法是Thread类中的一个方法，该方法的定义是等待该线程执行直到终止。
其实就说join方法将挂起调用线程的执行，直到被调用的对象完成它的执行。

join方法的示例在ThreadJoinTest.java文件中