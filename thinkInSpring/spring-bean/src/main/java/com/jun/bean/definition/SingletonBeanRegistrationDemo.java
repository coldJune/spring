package com.jun.bean.definition;

import com.jun.bean.factory.BookFactory;
import com.jun.bean.factory.DefaultBookFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 单体Bean注册示例
 * @Author dengxiaojun
 * @Date 2020/8/30 21:26
 * @Version 1.0
 */
@Configuration //Configuration class
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //创建外部单体Bean
        BookFactory bookFactory = new DefaultBookFactory();
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        //注册外部单体Bean
        singletonBeanRegistry.registerSingleton("bookFactory",bookFactory);
        //启动Spring 容器
        applicationContext.refresh();

        //通过依赖查找查找BookFactory
        BookFactory bookFactoryByLookUp = applicationContext.getBean("bookFactory",BookFactory.class);
        System.out.println("bookFactory == bookFactoryByLookUp:"+(bookFactory == bookFactoryByLookUp));
        //关闭Spring 容器
        applicationContext.close();

    }

}
