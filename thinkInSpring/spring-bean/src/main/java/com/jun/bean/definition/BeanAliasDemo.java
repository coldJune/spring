package com.jun.bean.definition;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Bean别名示例
 * @Author dengxiaojun
 * @Date 2020/8/17 22:33
 * @Version 1.0
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-definitions-context.xml");
        Book book = beanFactory.getBean("book", Book.class);
        //通过别名myBook获取曾用名book的Bean
        Book myBook = beanFactory.getBean("myBook",Book.class);
        System.out.println("myBook是否和book相同："+(book == myBook));

    }
}
