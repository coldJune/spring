package com.jun.bean.lifecycle;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化生命周期示例
 * @Author dengxiaojun
 * @Date 2020/11/30 22:31
 * @Version 1.0
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("---------");
        executeApplicationContext();

    }

    private static void executeBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 方法一：追加 BeanPostProcessor实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 基于XML 资源BeanDefinitionReader实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection-context.xml"};

        int beanDefinitionNums = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("加载 beanDefinition数量："+beanDefinitionNums);
        Book book =beanFactory.getBean("book", Book.class);
        System.out.println(book);

        Book cheapBook = beanFactory.getBean("cheapBook",Book.class);
        System.out.println(cheapBook);

        // 构造器注入按照类型注入 resolveDependency
        BookHolder bookHolder = beanFactory.getBean("bookHolder",BookHolder.class);
        System.out.println(bookHolder);
    }

    private static void executeApplicationContext(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();

        // 方法二： 将MyInstantiationAwareBeanPostProcessor作为Bean注册
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection-context.xml"};
        applicationContext.setConfigLocations(locations);
        // 启动应用上下文
        applicationContext.refresh();
        // 基于XML 资源BeanDefinitionReader实现


        Book book =applicationContext.getBean("book", Book.class);
        System.out.println(book);

        Book cheapBook = applicationContext.getBean("cheapBook",Book.class);
        System.out.println(cheapBook);

        // 构造器注入按照类型注入 resolveDependency
        BookHolder bookHolder = applicationContext.getBean("bookHolder",BookHolder.class);
        System.out.println(bookHolder);
        // 关闭应用上下文
        applicationContext.close();
    }

}
