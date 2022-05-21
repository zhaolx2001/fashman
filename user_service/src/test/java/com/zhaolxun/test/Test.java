package com.zhaolxun.test;

import com.zhaolxun.freshman.utils.DateUtils;

import java.util.Date;

/**
 * @author zhao
 * @create 2022-04-24 22:26
 */
public class Test {
    @org.junit.Test
    public void test1(){
        String s = "2019-12-31T16:00:00.000+0000";
        Date date = DateUtils.transferDateFormat(s);
        System.out.println(date);
    }
}
