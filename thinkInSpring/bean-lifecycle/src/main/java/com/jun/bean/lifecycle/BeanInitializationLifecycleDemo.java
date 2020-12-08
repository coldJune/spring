package com.jun.bean.lifecycle;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 初始化生命周期示例
 * @Author dengxiaojun
 * @Date 2020/12/7 14:48
 * @Version 1.0
 */
public class BeanInitializationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 追加 BeanPostProcessor实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加 CommonAnnotationBeanPostProcessor 解决@PostConstruct未生效的问题
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        // 基于XML 资源BeanDefinitionReader实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml","META-INF/bean-constructor-dependency-injection-context.xml"};

        int beanDefinitionNums = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("加载 beanDefinition数量："+beanDefinitionNums);
        // 显式地执行 preInstantiateSingletons
        // SmartInitializingSingleton 通常在Spring ApplicationContext场景使用
        // preInstantiateSingletons将已经注册的BeanDefinition初始化成Spring Bean
        beanFactory.preInstantiateSingletons();
        Book book =beanFactory.getBean("book", Book.class);
        System.out.println(book);

        Book cheapBook = beanFactory.getBean("cheapBook",Book.class);
        System.out.println(cheapBook);

        // 构造器注入按照类型注入 resolveDependency
        BookHolder bookHolder = beanFactory.getBean("bookHolder",BookHolder.class);

        System.out.println(bookHolder);

    }

}
