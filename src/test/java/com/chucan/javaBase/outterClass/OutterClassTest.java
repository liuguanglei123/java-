package com.chucan.javaBase.outterClass;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-09-03-18:31
 * @Description:
 */
public class OutterClassTest {

    public static void main(String[] args) {
        OutterClass outterClass = new OutterClass("value1","value2");
        outterClass.test();

        OutterClass.InnerClass1 innerClass1 = new OutterClass.InnerClass1();
//        OutterClass.InnerClass2 innerClass2 = new OutterClass.InnerClass2();

    }
}
