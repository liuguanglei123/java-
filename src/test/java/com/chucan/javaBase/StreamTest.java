package com.chucan.javaBase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-17-21:17
 * @Description:
 */
public class StreamTest {

    @Test
    public void testCreateStream(){
        //测试创建stream，一定要在一个数据源的基础上创建stream
        //1.通过集合创建stream
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
        Stream<Integer> stream1 = list.stream();

        //2.通过数组创建stream
        Integer[] arr = {1,2,3,4,5,6,7,8,9};
        Stream<Integer> stream2 = Arrays.stream(arr);

        //3.通过stream类的静态of方法创建
        Stream stream3 = Stream.of(1,2,3,4,5,6,7,8);

        //创建无限流
//        Stream stream4 = Stream.generate(() -> Math.random());
//        stream4.forEach(System.out::println);
        Stream stream4 = Stream.iterate(1, a -> a+1);
        stream4.forEach(System.out::println);
    }

    @Test
    public void testFilter(){
        Stream<Integer> stream3 = Stream.of(1,2,3,4,5,6,7,8);

        stream3.filter( e -> e>6 ).forEach(System.out::println);

    }

    @Test
    public void test04(){
        boolean result = Stream.of(1,3,5,7,9)
                .anyMatch(t -> t%2==0);
        System.out.println(result);
    }

    @Test
    public void test05(){
        Integer max = Stream.of(1,2,4,5,7,8)
                .reduce(1,(t1,t2) -> t1>t2?t1:t2);//BinaryOperator接口   T apply(T t1, T t2)
        System.out.println(max);
    }

    @Test
    public void test14() {
        List<Integer> list = Stream.of(1, 2, 4, 5, 7, 8)
                .filter(t -> t % 2 == 0)
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }

}
