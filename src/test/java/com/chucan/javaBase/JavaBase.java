package com.chucan.javaBase;

import org.junit.Test;

import java.util.Comparator;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-09-03-22:43
 * @Description:
 */
public class JavaBase {

    /*
        比较两个字符串长度
     */
    @Test
    public void ComparatorTest(){
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        System.out.println(comparator.compare("111111", "2222")>0);

    }

    @Test
    public void RunnableTest(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("新启动一个线程");
            }
        };

        runnable.run();
    }
}
