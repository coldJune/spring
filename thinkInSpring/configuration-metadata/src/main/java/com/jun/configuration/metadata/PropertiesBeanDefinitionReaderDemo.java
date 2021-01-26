package com.jun.configuration.metadata;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * {@link PropertiesBeanDefinitionReader}
 * @Author dengxiaojun
 * @Date 2021/1/26 下午9:40
 * @Version 1.0
 */
public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        // 创建 IoC 底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建面向 Properties 资源的 BeanDefinitionReader实例
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        // Properties 资源加载默认通过 ISO-8859-1，实际存储UTF-8
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        // 通过指定的 ClassPath 获取 Resource 对象
        Resource resource = resourceLoader.getResource("classpath:/META-INF/book-bean-definitions.properties");
        // 转换成带有字符编码 EncodedResource对象
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        int beanDefinitionCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println(String.format("已加载 %d 个 BeanDefinition\n",beanDefinitionCount));

        // 通过依赖查找获取Book bean
        Book book = beanFactory.getBean("book",Book.class);
        System.out.println(book);
    }
}
