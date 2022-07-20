package com.chucan.javaBase.lambdaTest;

import java.util.function.Function;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-17-17:06
 * @Description:
 */
public class FuntionTest {

    public static void useFunction(String str, Function<String,Integer> function){
        Integer apply = function.apply(str);
        System.out.println("执行的结果是："+ apply);
    }
}
