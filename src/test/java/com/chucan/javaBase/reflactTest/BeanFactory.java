package com.chucan.javaBase.reflactTest;

import org.junit.Test;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-16-22:36
 * @Description:
 */
public class BeanFactory {

    public static Object createBean(String className) throws Exception{
        Class clazz = Class.forName(className);
        return clazz.newInstance();
    }

    public static Object createBean(Class clazz) throws Exception{
        return clazz.newInstance();
    }

    @Test
    public void testReflact() throws Exception{
        //获取Class对象的几种方式
        User user = (User)BeanFactory.createBean("com.chucan.javaBase.reflactTest.User");
        System.out.println(user);

        User user2 = (User) BeanFactory.createBean(User.class);
        System.out.println(user2);

        User user3 = new User();
        User user4 = (User) BeanFactory.createBean(user3.getClass());
        System.out.println(user4);

    }
}
