package com.jun.dependency.injection.setter;

import com.jun.dependency.injection.BookHolder;
import com.jun.ioc.overview.dependency.domain.Book;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * "byName" Auto-wiring 依赖Setter方法注入示例
 * @Author dengxiaojun
 * @Date 2020/9/24 10:42
 * @Version 1.0
 */
public class AutoWiringByNameDependencySetterInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String xmlResourcePath = "classpath:/META-INFO/autowiring-dependency-setter-injection.xml";
        //加载 XML 资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        //依赖查找并创建Bean
        BookHolder bookHolder = beanFactory.getBean(BookHolder.class);
        System.out.println(bookHolder);
    }

}
