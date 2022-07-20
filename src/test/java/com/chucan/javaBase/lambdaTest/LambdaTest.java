package com.chucan.javaBase.lambdaTest;

import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-17-16:06
 * @Description:
 */
public class LambdaTest {

    public interface Calculator{
        int calc(int a,int b);
    }

    public static class CalculateManager{
        public static void invokeCalculate(int a,int b,Calculator calculator){
            System.out.println("result is "+calculator.calc(a, b));
        }
    }
    @Test
    public void testCal(){
        CalculateManager.invokeCalculate(3,2,(int a,int b) -> { return a+b; });
    }

    @Test
    public void testConsumer(){
        //第一种写法
        ConsumerTest.useConsumer("xxxxxxx", new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        //第二种写法，更简洁
        ConsumerTest.useConsumer("yyyyyyyy", (String str) -> {
            System.out.println(str);
        });

        //第三种写法，可以把数据类型省略
        ConsumerTest.useConsumer("yyyyyyyy", (str) -> {
            System.out.println(str);
        });

    }

    @Test
    public void testSupplier(){
        SupplierTest.useSupplier(() -> {
            return "zzzzzzzzz";
        });
    }

    @Test
    public void testPredicate(){
        PredicateTest.usePredicate("xyz" ,(str) -> {
            return "zzzzzzzzz".equals(str);
        });
    }

    @Test
    public void testFunction(){
        FuntionTest.useFunction("abcdefg" ,(str) -> {
            return str.length();
        });
    }

    @Test
    public void testListForeach(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
//        list.forEach(new Consumer<Integer> (){
//            @Override
//            public void accept(Integer i){
//                System.out.println(i);
//            }
//        });

        list.forEach(i -> System.out.println(i));
    }

    @Test
    public void testMapForEachTest(){
        Map<String,Integer> map = new HashMap<>();

        map.put("aaaa",1);
        map.put("bbbb",2);
        map.put("cccc",3);
        map.put("dddd",4);
        map.put("eeee",5);

//        map.forEach(new BiConsumer<String, Integer>() {
//            @Override
//            public void accept(String key, Integer value) {
//                System.out.println("key is " + key +  ",value is "+ value);
//            }
//        });

        map.forEach((key,value) -> System.out.println("key is " + key +  ",value is "+ value));
    }

    @Test
    public void replaceAllTest(){
        Map<String,Integer> map = new HashMap<>();

        map.put("aaaa",1);
        map.put("bbbb",2);
        map.put("cccc",3);
        map.put("dddd",4);
        map.put("eeee",5);

//        map.replaceAll(new BiFunction<String, Integer, Integer>() {
//            @Override
//            public Integer apply(String key, Integer value) {
//                if(key.equals("dddd"))
//                    return 99;
//                return value;
//            }
//        });

        map.replaceAll((k,v)->{
            if(k.equals("dddd"))
                return 99;
            return v;
        });

        map.forEach((key,value) -> System.out.println("key is " + key +  ",value is "+ value));
    }

    @Test
    public void testJDKPredicate(){
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

        list.forEach( e -> System.out.println(e));
        System.out.println("-----------------------");

        //移除元素为5的数字
        list.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer e) {
                return e.equals(5);
            }
        });

        list.removeIf( e -> e>=6 );

        list.forEach( e -> System.out.println(e));

    }

    @Test
    public void testSort(){
        Integer[] arr = {4,5,6,7,8,1,2,9,3};

//        Arrays.sort(arr, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2.compareTo(o1);
//            }
//        });

//        Arrays.sort(arr, (o1, o2) -> o2.compareTo(o1));

        Arrays.sort(arr, Integer::compareTo);


        Stream.of(arr).forEach(e -> System.out.println(e));
    }
}
