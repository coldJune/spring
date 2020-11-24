package com.jun.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 基于注解的BeanDefinition解析
 * @Author dengxiaojun
 * @Date 2020/11/24 22:28
 * @Version 1.0
 */
public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于Java注解的 AnnotatedBeanDefinitionReader实现
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        // 注册当前类(非 @Component class)
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

        System.out.println("已加载 BeanDefinition数量："+(beanDefinitionCountAfter-beanDefinitionCountBefore));

        // 普通的 AnnotatedBeanDefinitionParsingDemo Class 作为Component 注册到Spring IoC容器后
        // 通常 Bean 名称为 annotatedBeanDefinitionParsingDemo
        // Bean 名称生成来自于 BeanNameGenerator, 注解实现 AnnotatedBeanNameGenerator
        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo",AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(demo);
    }
}
