package com.jun.ioc.overview.dependency.injection;

import com.jun.ioc.overview.repository.BookRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        BookRepository bookRepository = (BookRepository) beanFactory.getBean("bookRepository");
        System.out.println(bookRepository.getBooks());
        //依赖注入
        System.out.println(bookRepository.getBeanFactory());
        System.out.println(bookRepository.getBeanFactory()==beanFactory);

        ObjectFactory bookFactory = bookRepository.getUserObjectFactory();
        System.out.println(bookFactory.getObject());

        ObjectFactory applicationContextObjectFactory = bookRepository.getApplicationContextObjectFactory();
        System.out.println(applicationContextObjectFactory.getObject()==beanFactory );
        //依赖查找（错误）
//        System.out.println(beanFactory.getBean(BeanFactory.class));


    }


}
