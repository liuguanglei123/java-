package com.chucan.javaBase;

import org.junit.Test;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-14-21:08
 * @Description:
 */
public class VolatileTest {

    public class Aobing extends Thread{
        private boolean flag = false;
//        private volatile boolean flag = false;

        public boolean isFlag(){
            return this.flag;
        }

        @Override
        public void run(){
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            flag = true;
            System.out.println("flag = " + flag);
        }
    }

    @Test
    public void test(){
        Aobing a = new Aobing();
        a.start();
        for(;;){
//            try{
//                Thread.sleep(300);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
            if(a.flag){
                System.out.println("testsssssss");
            }
        }
    }
}
