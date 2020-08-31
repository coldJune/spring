package com.jun.bean.definition;

import com.jun.bean.factory.BookFactory;
import com.jun.bean.factory.DefaultBookFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * {@link com.jun.bean.factory.BookFactory} 初始化Demo
 * @Author dengxiaojun
 * @Date 2020/8/30 21:26
 * @Version 1.0
 */
@Configuration //Configuration class
public class BeanInitializationDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration class(配置类)
        applicationContext.register(BeanInitializationDemo.class);
        //启动Spring 容器
        applicationContext.refresh();
        //非延迟初始化在Spring 应用上下文启动完成后，被初始化
        System.out.println("Spring 容器已启动");
        applicationContext.getBeansOfType(BookFactory.class);
        //关闭Spring 容器
        applicationContext.close();

    }

    @Bean(initMethod = "initBookFactoryByBean")
    @Lazy(false)
    public BookFactory bookFactory(){
        return new DefaultBookFactory();
    }
}
