package com.chucan.javaBase;

import org.junit.Test;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-16-20:12
 * @Description:
 */
public class EnumTest {
    public enum Gender {
        Male(1,"男"),
        Female(2,"女");

        private Integer type;

        private String genderStr;

        Gender(Integer type, String genderStr) {
            this.type=type;
            this.genderStr = genderStr;
        }

        public String toString(){
            return type + genderStr;
        }
    }

    public class Person{
        private String name;

        private Gender gender;

        public Person(String name,Gender gender){
            this.name = name;
            this.gender = gender;
        }

        public String toString(){
            return "name is :" + name + ",gender is :" + gender;
        }
    }

    @Test
    public void enumTest(){
        Person person = new Person("zhangsan",Gender.Male);
        System.out.println(person.toString());
    }
}
