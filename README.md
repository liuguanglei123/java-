# java-
java基础，你真的了解吗？

## 拓展一下：Callable接口
基础的几种集合 list set map treeset treemap
基础的字符串类 string stringbuilder stringbuffer
复杂一点的数据结构 二叉树 红黑树 B树
git基础
时间日期类
bigdecimal与interger类
IO流
位运算
运算符重载
Cumsume Function 等区别
匿名内部类
可变数量的形参
javaBean

锁 自旋锁
hashmap hashtable

properties集合
多线程 继承thread runnable callable 如何启动一个新的线程 
Lock接口 实现类 ReentrantLock
juc java utils concurrent并发

脏读幻读不可重复读

什么是servlet

线程状态和线程池状态

依赖倒置原则

Exception RuntimeException Error 区别

final Map<Long, UserDto> userMap = users.stream().collect(Collectors.toMap(
                UserDto::getUserId,
                Function.identity(),
                (a, b) -> a
        ));