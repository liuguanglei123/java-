package com.chucan.javaBase.singleDesignMode;

import org.junit.Test;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-15-0:10
 * @Description:
 */
public class SingleDesignMode {

    /**
     * 饿汉式
     */
    @Test
    public void testHungry(){
        HungryDemo demo = HungryDemo.getInstance();
        System.out.println(demo);
    }

    /**
     * 饱汉式
     */
    @Test
    public void testLazy(){
        LazyDemo demo = LazyDemo.getInstance();
        System.out.println(demo);
    }
}
