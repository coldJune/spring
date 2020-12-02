package com.jun.bean.lifecycle;

import com.jun.ioc.overview.domain.Book;
import com.jun.ioc.overview.domain.CheapBook;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化生命周期示例
 * @Author dengxiaojun
 * @Date 2020/11/30 22:31
 * @Version 1.0
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 追加 BeanPostProcessor实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 基于XML 资源BeanDefinitionReader实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml","META-INF/bean-constuctor-rdependency-injection-context.xml"};

        int beanDefinitionNums = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("加载 beanDefinition数量："+beanDefinitionNums);
        Book book =beanFactory.getBean("book", Book.class);
        System.out.println(book);

        Book cheapBook = beanFactory.getBean("cheapBook",Book.class);
        System.out.println(cheapBook);

        // 构造器注入按照类型注入 resolveDependency
        BookHolder bookHolder = beanFactory.getBean("bookHolder",BookHolder.class);
        System.out.println(bookHolder);

    }

    static class MyInstantiationAwareBeanPostProcessor
            implements InstantiationAwareBeanPostProcessor{
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("cheapBook",beanName)
                    && CheapBook.class.equals(beanClass)){
                // 将配置完成的CheapBook 覆盖
                return new CheapBook();
            }else{
                return null; // 保持Spring IoC的实例化操作
            }
        }
    }


}