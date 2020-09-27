package com.jun.dependency.injection.aware;

import com.jun.dependency.injection.BookHolder;
import com.jun.ioc.overview.dependency.domain.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于{@linke @Aware} 接口回调的注入示例
 * @Author dengxiaojun
 * @Date 2020/9/24 10:42
 * @Version 1.0
 */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {



    private static BeanFactory beanFactory;
    private static ApplicationContext applicationContext;
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册Configuration Class（配置类）-->spring Bean
        context.register(AwareInterfaceDependencyInjectionDemo.class);

        //启动Spring 应用上下文
        context.refresh();

        System.out.println(beanFactory == context.getBeanFactory());
        System.out.println(applicationContext == context);

        context.close();
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }

}
