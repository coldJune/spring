package com.jun.bean.definition;

import com.jun.bean.factory.BookFactory;
import com.jun.bean.factory.DefaultBookFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.Provider;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊的Bean 实例化示例
 * @Author dengxiaojun
 * @Date 2020/8/29 21:25
 * @Version 1.0
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/special-bean-instantiation-context.xml");
        // 通过ApplicationContext 获取AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        ServiceLoader<BookFactory> serviceLoader = beanFactory.getBean("bookFactoryServiceLoader",ServiceLoader.class);
        displayServiceLoader(serviceLoader);

        //创建BookFactory对象通过AutowireCapableBeanFactory
        BookFactory bookFactory = beanFactory.createBean(DefaultBookFactory.class);
        System.out.println(bookFactory.createBook());
//        demoServiceLoader();
    }
    public static void demoServiceLoader(){
        ServiceLoader<BookFactory> serviceLoader = ServiceLoader.load(BookFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    private static void displayServiceLoader(ServiceLoader serviceLoader){
        Iterator<BookFactory> iterator = serviceLoader.iterator();
        while(iterator.hasNext()){
            BookFactory bookFactory = iterator.next();
            System.out.println(bookFactory.createBook());
        }
    }
}
