package com.jun.dependency.injection.setter;

import com.jun.dependency.injection.BookHolder;
import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解 资源的依赖Setter方法注入示例
 * @Author dengxiaojun
 * @Date 2020/9/24 10:42
 * @Version 1.0
 */
public class AnnotationDependencySetterInjectionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class（配置类）
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        //启动Spring 应用上下文
        applicationContext.refresh();

        //依赖查找并创建Bean
        BookHolder bookHolder = applicationContext.getBean(BookHolder.class);
        System.out.println(bookHolder);
        //显示关闭Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public BookHolder bookHolder(Book book){

//        return new BookHolder(book);
        BookHolder bookHolder = new BookHolder();
        bookHolder.setBook(book);
        return bookHolder;
    }
}
