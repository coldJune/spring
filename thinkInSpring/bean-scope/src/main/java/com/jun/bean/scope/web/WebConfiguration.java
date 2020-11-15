package com.jun.bean.scope.web;

import com.jun.ioc.overview.domain.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Random;

/**
 * @Author dengxiaojun
 * @Date 2020/11/15 20:07
 * @Version 1.0
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {
    @Bean
    @RequestScope
    public Book book(){
        Book book = new Book();
        book.setPrice(new Random().nextInt());
        return book;
    }
}
