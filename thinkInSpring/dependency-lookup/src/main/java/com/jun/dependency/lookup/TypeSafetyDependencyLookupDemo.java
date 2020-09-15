package com.jun.dependency.lookup;

import com.jun.ioc.overview.dependency.domain.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 类型安全  依赖查找示例
 * @Author dengxiaojun
 * @Date 2020/9/3 21:45
 * @Version 1.0
 */
public class TypeSafetyDependencyLookupDemo {//@Configuration是非必须注解
    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //TypeSafetyDependencyLookupDemo 作为配置类(Configuration Class)
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //演示 BeanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);

        //演示 ObjectFactory#getObjedct 方法的安全性
        displayObjectFactoryGetObject(applicationContext);

        //演示 ObjectProvider#getIfAvailable 方法的安全性
        displayObjectProviderIfAvailable(applicationContext);

        //演示 ListableBeanFactory#getBeansOfType 方法的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);

        //演示 ObjectProvider Stream操作的安全性
        displayObjectProviderStreamOps(applicationContext);
        //关闭应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<Book> bookObjectProvider = applicationContext.getBeanProvider(Book.class);
        printBeansException("displayObjectProviderStreamOps",()->bookObjectProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory listableBeanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType",()->listableBeanFactory.getBeansOfType(Book.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<Book> bookObjectProvider = applicationContext.getBeanProvider(Book.class);
        printBeansException("displayObjectProviderIfAvailable",()->bookObjectProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        //ObjectFactory is ObjectProvider
        ObjectFactory<Book> bookObjectFactory = applicationContext.getBeanProvider(Book.class);
        printBeansException("displayObjectFactoryGetObject",()->bookObjectFactory.getObject());
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean",() ->{
            beanFactory.getBean(Book.class);
        });
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("================");
        System.err.println("from: "+source);
        try {
            runnable.run();
        }catch (BeansException e){
            e.printStackTrace();
        }
    }


}
