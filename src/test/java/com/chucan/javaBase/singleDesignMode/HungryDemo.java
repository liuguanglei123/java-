package com.chucan.javaBase.singleDesignMode;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-15-0:15
 * @Description:
 */
public class HungryDemo{

    private static HungryDemo h = new HungryDemo();

    private HungryDemo(){

    }

    public static HungryDemo getInstance(){
        return h;
    }
}