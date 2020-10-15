package com.jun.dependency.injection.lazy;

import com.jun.dependency.injection.annotaion.BookGroup;
import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Set;

/**
 * 基于{@link ObjectProvider} 实现延迟依赖注入
 * @Author dengxiaojun
 * @Date 2020/9/24 10:42
 * @Version 1.0
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private Book book; // cheapBook -> primary = true

    @Autowired
    private ObjectProvider<Book> bookObjectProvider;

    @Autowired
    private ObjectFactory<Set<Book>> bookObjectFactory;

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class（配置类）
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        //启动Spring 应用上下文
        applicationContext.refresh();

        //依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        // 期待输出cheapBook Bean
        System.out.println("demo.book:"+demo.book);

        // 期待输出cheapBook Bean
        System.out.println("demo.bookObjectProvider:"+demo.bookObjectProvider.getObject());// 继承自ObjectFactory

        // 期待输出cheapBook book Bean
        System.out.println("demo.bookObjectFactory:"+demo.bookObjectFactory.getObject());

        demo.bookObjectProvider.forEach(System.out::println);
        //显示关闭Spring 应用上下文
        applicationContext.close();
    }


}
