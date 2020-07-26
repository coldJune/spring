package com.jun.ioc.overview.dependency.lookup;

import com.jun.ioc.overview.annotation.Cheap;
import com.jun.ioc.overview.dependency.domain.Book;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/** 依赖查找示例
 * 1.通过名称的方式来查找
 *
 * @Author dengxiaojun
 * @Date 2020/7/26 18:33
 * @Version 1.0
 */
public class DependencyLookUpDemo {
    public static void main(String[] args) {
        //配置XML配置文件
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
//        Book book = (Book) beanFactory.getBean("book");
//        System.out.println(book);
        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);
        lookupByType(beanFactory);
        lookupCollectionType(beanFactory);
        lookupAnnotation(beanFactory);
    }

    /**
     * 根据注解查找
     * @param beanFactory
     */
    private static void lookupAnnotation(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String,Book> books = (Map)listableBeanFactory.getBeansWithAnnotation(Cheap.class);
            System.out.println("查找标注为@Cheap所有的Book对象："+books);
        }
    }

    /**
     * 根据类型查找集合对象
     * @param beanFactory
     */
    private static void lookupCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String,Book> books = listableBeanFactory.getBeansOfType(Book.class);
            System.out.println("查找到所有的Book对象："+books);
        }
    }


    /**
     * 根据类型查找
     * @param beanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        Book book = beanFactory.getBean(Book.class);
        System.out.println("根据类型查找："+book);
    }

    /**
     * 延迟查找
     * @param beanFactory
     */
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<Book> objectFactory = (ObjectFactory<Book>)beanFactory.getBean("objectFactory");
        Book book = objectFactory.getObject();
        System.out.println("延迟查找："+book);
    }

    /**
     * 实时查找
     * @param beanFactory
     */
    private static void lookupInRealTime(BeanFactory beanFactory){
        Book book = (Book) beanFactory.getBean("book");
        System.out.println("实时查找："+book);
    }
}
