package com.chucan.javaBase.noUse;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-09-03-22:32
 * @Description:
 */
public class test implements interfacetest1,interfacetest2{


    public static void main(String[] args) {
        test test1 = new test();
        System.out.println(test1.getId());
    }

    @Override
    public int getId() {
        return 0;
    }
}
