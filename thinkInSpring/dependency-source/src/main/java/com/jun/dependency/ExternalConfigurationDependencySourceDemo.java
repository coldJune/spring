package com.jun.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源
 * @Author dengxiaojun
 * @Date 2020/10/29 21:37
 * @Version 1.0
 */
@PropertySource(value = "classpath:META-INF/default.properties",encoding = "UTF-8")
@Configuration
public class ExternalConfigurationDependencySourceDemo {
    @Value("${user.age:0}")
    private Long age;

    @Value("${use.name:俊}")
    private String name;

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(ExternalConfigurationDependencySourceDemo.class);
        // 启动Spring 容器
        annotationConfigApplicationContext.refresh();
        ExternalConfigurationDependencySourceDemo demo = annotationConfigApplicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println("demo.age="+demo.age);
        System.out.println("demo.name="+demo.name);

        // 关闭Spring容器
        annotationConfigApplicationContext.close();
    }

}
