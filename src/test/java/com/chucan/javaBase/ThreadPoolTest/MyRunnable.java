package com.chucan.javaBase.ThreadPoolTest;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-16-15:35
 * @Description:
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
    }
}
