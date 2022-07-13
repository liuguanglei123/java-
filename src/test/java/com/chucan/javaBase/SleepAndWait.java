package com.chucan.javaBase;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-05-22:37
 * @Description:
 */
@Slf4j
public class SleepAndWait {

    /**
     * Thread.sleep(long millis)
     * 在指定的毫秒数内让正在执行的线程休眠（暂停执行），此操作受到系统计时器和调度程序精度和准确性的影响
     *
     * @throws InterruptedException
     */
    @Test
    public void testSleep() throws InterruptedException {
        StopWatch stopWatch = StopWatch.createStarted();
        Thread.sleep(4000);
        System.out.println(stopWatch.getTime());
        stopWatch.stop();
    }

    @Test
    public void testWait() {
        StopWatch stopWatch = StopWatch.createStarted();
        Object obj = new Object();
        try {
            obj.wait(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stopWatch.getTime());
        stopWatch.stop();
    }

    @Test
    public void testWait2() throws InterruptedException {
        Object lock = new Object();
        Thread A = new Thread(() -> {
            synchronized (lock) {
                log.info("线程A获取了锁");
                try {
                    log.info("休眠一会儿");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("线程A即将进入wait状态，此时锁将被释放...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("A被唤醒");
            }
        }, "Thread-A");

        A.start();

        Thread B = new Thread(() -> {
            synchronized (lock) {
                log.info("线程B获得了锁");
                log.info("线程B即将释放锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-B");

        B.start();

        Thread.sleep(4000);
    }


    /**
     * notify和wait的另外一种演示方法
     */
    public class MyThread implements Runnable {
        private boolean flag;

        private Object object;

        public MyThread(boolean flag, Object object) {
            this.flag = flag;
            this.object = object;
        }

        public void waitThead() {
            synchronized (object) {
                log.info(Thread.currentThread().getName() + " wait begin...");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    log.info("出现错误", e);
                }
                log.info(Thread.currentThread().getName() + " wait end!!!");
            }
        }

        public void notifyThread() {
            synchronized (object) {
                log.info(Thread.currentThread().getName() + " notify begin...");

                object.notify();

                log.info(Thread.currentThread().getName() + " notify end!!!");
            }
        }

        public void notifyAllThread() {
            synchronized (object) {
                log.info(Thread.currentThread().getName() + " notifyAll begin...");

                object.notifyAll();

                log.info(Thread.currentThread().getName() + " notifyAll end!!!");
            }
        }

        @Override
        public void run() {
            if (flag) {
                waitThead();
            } else {
                notifyThread();
//                notifyAllThread();
            }
        }
    }

    @Test
    public void testWaitAndNotify() throws InterruptedException {
        Object obj = new Object();

        MyThread notifyMyThread = new MyThread(false, obj);
        Thread notifyThread = new Thread(notifyMyThread,"Thread-Notify");

        for (int i = 0; i < 10; i++) {
            MyThread waitMyThread = new MyThread(true, obj);
            Thread waitThread = new Thread(waitMyThread,"Thread-wait-" + i);
            waitThread.start();
        }
        Thread.sleep(1000);
        notifyThread.start();

    }

}
