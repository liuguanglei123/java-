# 一些无法归类的学习内容放在这里

## 内部类
内部测试类在src\test\java\com\chucan\javaBase\outterClass包下

内部类可以分为两大类：带static和不带static，两者有何区别：
1. 带static的类可以单独使用，在其他类中进行new操作，不带static的则不行
2. 带static的内部类可以在其他类中单独使用，但是不带static的内部类，是真·内部类，不能单独使用，必须以来外部类的对象
3. 不带static的内部类，可以获取外部类的对象，如OutterClass类中的如下一行内容
``` 
    System.out.println(OutterClass.this.outterValue1);
    System.out.println(outterValue1);
```
思考：
lambda表达式也是内部类转化而来，是否和这里的内部类使用有千丝万缕的联系？

大胆猜测一下，lambda表达式实际为不带static的内部类，所以lambda表达式中可以用外部类对象的属性值。

## 一些需要烂熟于心的接口
### Comporator
可以自定义数据比较规则，常用在类中没有默认比较规则或者自定义比较规则场景，一个最简单的示例：
```
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
```

### Runnable接口
多线程接口，实现接口类似 new Thread的效果
