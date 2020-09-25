package com.jun.dependency.injection.constructor;

import com.jun.dependency.injection.BookHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * "construct-arg" Auto-wiring 依赖 constructor方法注入示例
 * @Author dengxiaojun
 * @Date 2020/9/24 10:42
 * @Version 1.0
 */
public class AutoWiringByNameDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String xmlResourcePath = "classpath:/META-INFO/dependency-constructor-injection.xml";
        //加载 XML 资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        //依赖查找并创建Bean
        BookHolder bookHolder = beanFactory.getBean(BookHolder.class);
        System.out.println(bookHolder);
    }

}
