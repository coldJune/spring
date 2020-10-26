package com.jun.dependency;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 * @Author dengxiaojun
 * @Date 2020/10/26 21:24
 * @Version 1.0
 */
public class DependencySourceDemo {
    // 注入在postProcessProperties方法中执行，早于setter注入，也早于@PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 依赖注入的依赖来源比依赖查找的依赖来源多了一个非Spring容器管理对象，
     * 共包含4个类型两个对象
     */
    @PostConstruct
    public void initByInjection(){
        System.out.println("beanFactory == applicationContext:"+(beanFactory==applicationContext));//false
        System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory:"+(beanFactory==applicationContext.getAutowireCapableBeanFactory()));//true
        System.out.println("resourceLoader == applicationContext:"+(resourceLoader==applicationContext));//true
        System.out.println("applicationEventPublisher == applicationContext:"+(applicationEventPublisher==applicationContext));//true

    }

    @PostConstruct
    public void initByLookup(){
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
        getBean(ApplicationContext.class);
    }

    private <T> T getBean(Class<T> beanType){
        try {
            return beanFactory.getBean(beanType);
        }catch (NoSuchBeanDefinitionException e){
            System.err.println("类型为["+beanType.getName()+"] 的对象在 beanFactory中找不到");
        }
        return null;

    }
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        // 注册当前类为Configuration Class -> Spring Bean
        annotationConfigApplicationContext.register(DependencySourceDemo.class);

        // 启动Spring 应用上下文
        annotationConfigApplicationContext.refresh();
        // 关闭Spring 应用上下文
        annotationConfigApplicationContext.close();
    }
}
