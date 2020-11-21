package com.jun.bean.lifecycle;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;


/**
 * Bean 元信息配置示例
 * @Author dengxiaojun
 * @Date 2020/11/21 21:42
 * @Version 1.0
 */
public class BeanMetadataConfiguration {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        // 加载Properties 资源
        // 指定编码为UTF-8
        String location = "META-INF/book.properties";
        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        int beanDefinitionNums = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("加载 beanDefinition数量："+beanDefinitionNums);
        Book  book =beanFactory.getBean("book", Book.class);
        System.out.println(book);
    }
}
