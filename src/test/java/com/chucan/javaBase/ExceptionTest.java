package com.chucan.javaBase;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-06-10:04
 * @Description:
 */
@Slf4j
public class ExceptionTest {

    @Test
    public void test1(){
        try{
            throwException();
        }catch(Exception e){
            log.error("报错了呢,报错内容是：{}", "下游报错了", e);
        }
    }

    public Integer throwException() throws Exception {
        throw new Exception(" this is error message");
    }
}
