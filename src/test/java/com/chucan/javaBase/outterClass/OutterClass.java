package com.chucan.javaBase.outterClass;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-09-03-18:45
 * @Description:
 */
public class OutterClass {

    public static String outterValue0 = "OutterClassTEST";

    private String outterValue1;

    private String outterValue2;

    private InnerClass2 innerClass2;

    private InnerClass1 innerClass1;

    public static class InnerClass1{
        private String innerValue1;
        private String innerValue2;
        private String innerValue3;

        public void func1(){

        }
    }

    public class InnerClass2{
        private String innerValue4;
        private String innerValue5;
        private String innerValue6;

        public void func2(){
            System.out.println(OutterClass.this.outterValue1);
            System.out.println(outterValue1);
        }
    }

    public OutterClass(String value1,String value2){
        outterValue1 = value1;
        outterValue2 = value2;
        innerClass1 = new InnerClass1();
        innerClass2 = new InnerClass2();
    }

    public void test(){
        innerClass2.func2();
    }
}
