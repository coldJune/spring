package com.jun.configuration.metadata;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * Spring XML 元素扩展示例
 * @author: dengxiaojun-001
 * @date 2021/3/1 15:16
 * Description:
 */
public class ExtensibleXmlAuthoringDemo {
    public static void main(String[] args) {
        // 创建 IoC 底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建XML资源的 BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 加载 XML 资源
        reader.loadBeanDefinitions("META-INF/book-context.xml");
        // 获取Book bean对象
        Book book = beanFactory.getBean(Book.class);
        System.out.println(book);
    }
}
