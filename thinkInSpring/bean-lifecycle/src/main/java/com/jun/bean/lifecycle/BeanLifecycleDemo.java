package com.jun.bean.lifecycle;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

public class BeanLifecycleDemo {
    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 追加 BeanPostProcessor实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加 DestructionAwareBeanPostProcessor 执行销毁前回调 遵循先入先出，所以这个需要放在 CommonAnnotationBeanPostProcessor之前，否则preDestroy会先于 postProcessBeforeDestruction执行
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
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

        // 执行 Bean销毁（容器内）
        beanFactory.destroyBean("bookHolder", bookHolder);
        // Bean 销毁并不意味着Bean 被垃圾回收了
        System.out.println(bookHolder);
        beanFactory.destroySingletons();
        bookHolder = null;// 设置成null才能被回收，否则该方法中存在对象引用，不会回收
        // 强制GC
        System.gc();
        Thread.sleep(1000);

    }
}
