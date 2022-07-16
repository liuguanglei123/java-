package com.chucan.javaBase.ThreadPoolTest;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-16-15:33
 * @Description:
 */
public class ThreadTest {
    @Test
    public void test1(){
        //创建线程池，线程数量为3
        ExecutorService es = Executors.newFixedThreadPool(3);
        //线程池管理对象service，调用方法submit提交线程的任务
        MyRunnable mr = new MyRunnable();
        es.submit(mr);
        es.submit(mr);
        es.submit(mr);
        //销毁线程池
        es.shutdown();
    }

    public static void main(String[] args){
        //创建线程池，线程数量为3
        ExecutorService es = Executors.newFixedThreadPool(3);
        //线程池管理对象service，调用方法submit提交线程的任务
        MyRunnable mr = new MyRunnable();
        es.submit(mr);
        //销毁线程池
        es.shutdown();

    }
}
