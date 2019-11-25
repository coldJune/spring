package com.coldjune.readinglistwithtest;

import com.coldjune.readinglistwithtest.handler.ReaderHandlerMethodArgumentResolver;
import com.coldjune.readinglistwithtest.model.Reader;
import com.coldjune.readinglistwithtest.service.ReaderRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
//开启组件扫描和自动配置
//相当于@Configuration/@ComponentScan/@EnableAutoConfiguration一起使用
//@Configuration 标明该类使用Spring基于Java的配置
//@ComponentScan 启用组件扫描，这样web控制器类和其它组件才能被自动发现并注册为Spring应用程序的上下文
//@EnableAutoConfiguration 开启Spring Boot的自动配置
public class ReadinglistwithtestApplication implements WebMvcConfigurer {

    @Bean
    InitializingBean saveData(ReaderRepository readerRepository){
        return ()->{
            Reader reader = new Reader();
            reader.setFullname("coldJune");
            reader.setUsername("coldJune");
            reader.setPassword("coldJune");
            readerRepository.save(reader);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(ReadinglistwithtestApplication.class, args);
    }


}
