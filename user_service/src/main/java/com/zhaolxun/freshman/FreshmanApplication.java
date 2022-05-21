package com.zhaolxun.freshman;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhao
 * @create 2022-04-24 10:48
 */
@SpringBootApplication
@ComponentScan("com.zhaolxun")
@MapperScan("com.zhaolxun.freshman.mapper")
public class FreshmanApplication {
    public static void main(String[] args) {
        SpringApplication.run(FreshmanApplication.class, args);
    }
}
