package com.jun.ioc.overview.dependency.injection;

import com.jun.ioc.overview.repository.BookRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/** 依赖注入示例
 *
 * @Author dengxiaojun
 * @Date 2020/7/26 18:33
 * @Version 1.0
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        //配置XML配置文件
        //启动Spring应用上下文
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
       //依赖来源一：自定义的Bean
        BookRepository bookRepository = (BookRepository) applicationContext.getBean("bookRepository");
        System.out.println(bookRepository.getBooks());
        //依赖来源二：依赖注入(内建依赖)
        System.out.println(bookRepository.getBeanFactory());

        ObjectFactory bookFactory = bookRepository.getUserObjectFactory();
        System.out.println(bookFactory.getObject());

        ObjectFactory applicationContextObjectFactory = bookRepository.getApplicationContextObjectFactory();
        System.out.println(applicationContextObjectFactory.getObject()==applicationContext );

        //依赖查找（错误）
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        //依赖来源三：容器内建的Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取容器的内建Environment Bean："+environment);


    }

    private static void whoIsIoCContainer(BookRepository bookRepository,ApplicationContext applicationContext){
        //这个表达是为什么不成立
        System.out.println(bookRepository.getBeanFactory()==applicationContext);

        //applicationContext is BeanFactory
        //BeanFactory是底层的IoC容器，applicationContext在之上增加了一些特性
        //applicationContext是BeanFactory的一个超集，它在底层是通过组合引入了一个beanFactory
        //当要获取beanFactory时需要调getBeanFactory获取真正的beanFactory
    }
}
