package com.chucan.javaBase;

import org.junit.Test;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-16-19:46
 * @Description:
 */
public class ThreadJoinTest {

    @Test
    public void ThreadJoinTest1(){
        //初始化线程1，由于后续有匿名内部类调用这个局部变量，需要用final修饰
        //这里不用final修饰也不会报错的原因 是因为jdk1.8对其进行了优化
        /*
        在局部变量没有重新赋值的情况下，它默认局部变量为final类型，认为你只是忘记了加final声明了而已。
        如果你重新给局部变量改变了值或者引用，那就无法默认为final了
         */
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 is running...");
            }
        });

        //初始化线程二
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("t2 is running...");
                }
            }
        });

        //初始化线程三
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("t3 is running...");
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
