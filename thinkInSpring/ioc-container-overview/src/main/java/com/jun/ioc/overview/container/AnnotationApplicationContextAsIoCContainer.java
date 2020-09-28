package com.jun.ioc.overview.container;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/** 注解能力{@link ApplicationContext}作为IoC  容器示例
 * @Author dengxiaojun
 * @Date 2020/7/27 22:26
 * @Version 1.0
 */
public class AnnotationApplicationContextAsIoCContainer {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类AnnotationApplicationContextAsIoCContainer作为配置类(Configuration Class)
        applicationContext.register(AnnotationApplicationContextAsIoCContainer.class);
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找集合对象
        lookupCollectionType(applicationContext);
        //关闭
        applicationContext.close();
    }

    /**
     * 通过java注解的方式定义了一个Bean
     * @return
     */
    @Bean
    public Book book(){
        Book book = new Book();
        book.setPrice(5);
        book.setName("spring");
        return book;
    }
    /**
     * 根据类型查找集合对象
     * @param beanFactory
     */
    private static void lookupCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, Book> books = listableBeanFactory.getBeansOfType(Book.class);
            System.out.println("查找到所有的Book对象："+books);
        }
    }
}
