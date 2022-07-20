package com.chucan.javaBase.lambdaTest;

import java.util.function.Consumer;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-17-16:37
 * @Description:
 */
public class ConsumerTest {

    public static void useConsumer(String str, Consumer<String> consumer){
        consumer.accept(str);
    }
}
