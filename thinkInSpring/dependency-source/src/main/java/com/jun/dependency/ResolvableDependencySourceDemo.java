package com.jun.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源
 * @Author dengxiaojun
 * @Date 2020/10/29 21:37
 * @Version 1.0
 */
public class ResolvableDependencySourceDemo {
    @Autowired
    private String value;

    @PostConstruct
    public void init(){
        System.out.println(value);
    }
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(ResolvableDependencySourceDemo.class);
        annotationConfigApplicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "test");
        });
        // 启动Spring 容器
        annotationConfigApplicationContext.refresh();

        // 关闭Spring容器
        annotationConfigApplicationContext.close();
    }

}
