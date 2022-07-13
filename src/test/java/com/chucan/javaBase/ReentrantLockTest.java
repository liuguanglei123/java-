package com.chucan.javaBase;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-13-0:08
 * @Description:
 */
public class ReentrantLockTest {

    private Integer count;

    private Boolean flag;

    private Lock lock = new ReentrantLock(true); //Lock接口实现类对象

    //Lock接口锁，创建两个阻塞队列
    private Condition producer = lock.newCondition();// 生产者线程阻塞队列
    private Condition consumer = lock.newCondition();// 消费者线程阻塞队列

    public ReentrantLockTest(){
        count = 0;
        flag = false;
    }

    //消费者调用
    public void getCount(){
        lock.lock();//获取锁
        System.out.println("线程："+Thread.currentThread().getName()+"获取到了锁");
        while(!flag){
            //消费者线程等待，执行到这里的线程，释放锁，并且将当前线程加入到消费者等待队列
            try {
                consumer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费第"+count+"个,线程是："+Thread.currentThread().getName());

        System.out.println("反转flag，当前状态是"+flag+"线程是："+Thread.currentThread().getName());

        flag = false;

        //唤醒生产者线程队列中的一个
        producer.signal();
        System.out.println("线程："+Thread.currentThread().getName()+"即将释放锁");
        lock.unlock();//释放锁
    }

    //生产者调用
    public void setCount(){
        lock.lock();//获取锁
        System.out.println("线程："+Thread.currentThread().getName()+"获取到了锁");
        while(flag){
            //等待，生产者线程等待，执行到这里的线程，释放锁，并且将当前线程加入到生产者等待队列
            try {
                producer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count++;
        System.out.println("生产第"+count+"个,线程是："+Thread.currentThread().getName());

        System.out.println("反转flag，当前状态是"+flag+"线程是："+Thread.currentThread().getName());
        flag = true;

        //唤醒消费者者线程队列中的一个
        consumer.signal();
        System.out.println("线程："+Thread.currentThread().getName()+"即将释放锁");

        lock.unlock();//释放锁
    }

    @Test
    public void test() throws InterruptedException {
        ReentrantLockTest r = new ReentrantLockTest();
        //接口实现类,生产的,消费的
        Produce produce = new Produce(r);
        Customer customer = new Customer(r);
        //创建线程
        new Thread(produce).start();
        new Thread(produce).start();
        new Thread(produce).start();

        new Thread(customer).start();
        new Thread(customer).start();
        new Thread(customer).start();

    }

    public class Produce implements Runnable{

        private ReentrantLockTest r ;

        public Produce(ReentrantLockTest r) {
            this.r = r;
        }

        @Override
        public void run() {
            while (true) {
                r.setCount();
                System.out.println("执行下一次");
            }
        }
    }

    public class Customer implements Runnable{
        private ReentrantLockTest r ;

        public Customer(ReentrantLockTest r) {
            this.r = r;
        }

        @Override
        public void run() {
            while (true) {
                r.getCount();
                System.out.println("执行下一次");
            }
        }
    }

}
