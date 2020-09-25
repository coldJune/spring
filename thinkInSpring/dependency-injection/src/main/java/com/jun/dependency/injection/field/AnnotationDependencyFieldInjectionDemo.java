package com.jun.dependency.injection.field;

import com.jun.dependency.injection.BookHolder;
import com.jun.ioc.overview.dependency.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解 资源字段注入示例
 * @Author dengxiaojun
 * @Date 2020/9/24 10:42
 * @Version 1.0
 */
public class AnnotationDependencyFieldInjectionDemo {
    @Autowired
    private
    //static @Autowired会忽略掉静态字段
    BookHolder bookHolder;

    @Autowired
    private BookHolder bookHolder2;

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class（配置类）
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        //启动Spring 应用上下文
        applicationContext.refresh();

        //依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        //@Autowired字段关联
        BookHolder bookHolder = demo.bookHolder;
        System.out.println(bookHolder);
        System.out.println(demo.bookHolder2);
        System.out.println(bookHolder == demo.bookHolder2);
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
