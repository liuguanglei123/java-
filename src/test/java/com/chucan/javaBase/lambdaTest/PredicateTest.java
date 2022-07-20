package com.chucan.javaBase.lambdaTest;

import java.util.function.Predicate;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-17-16:56
 * @Description:
 */
public class PredicateTest {

    public static void usePredicate(String str, Predicate<String> predicate){
        boolean test = predicate.test(str);
        System.out.println("结果是："+ test);
    }
}
