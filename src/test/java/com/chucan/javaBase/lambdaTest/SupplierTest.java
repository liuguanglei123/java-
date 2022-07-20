package com.chucan.javaBase.lambdaTest;

import java.util.function.Supplier;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-17-16:53
 * @Description:
 */
public class SupplierTest {
    public static void useSupplier(Supplier<String> supplier){
        String result = supplier.get();
        System.out.println("供给型接口返回值为"+result);
    }
}
