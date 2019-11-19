package com.coldjune.readinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//开启组件扫描和自动配置
//相当于@Configuration/@ComponentScan/@EnableAutoConfiguration一起使用
//@Configuration 标明该类使用Spring基于Java的配置
//@ComponentScan 启用组件扫描，这样web控制器类和其它组件才能被自动发现并注册为Spring应用程序的上下文
//@EnableAutoConfiguration 开启Spring Boot的自动配置
public class ReadingListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadingListApplication.class, args);
    }

}
