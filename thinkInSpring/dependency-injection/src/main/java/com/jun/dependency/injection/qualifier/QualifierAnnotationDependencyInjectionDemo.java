package com.jun.dependency.injection.qualifier;

import com.jun.dependency.injection.BookHolder;
import com.jun.dependency.injection.annotaion.BookGroup;
import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 基于{@link Qualifier} 限定注入
 * @Author dengxiaojun
 * @Date 2020/9/24 10:42
 * @Version 1.0
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private Book book; // cheapBook -> primary = true

    @Autowired
    @Qualifier("book")
    private Book saleBook; // 指定Bean名称或ID


    @Autowired
    private Collection<Book> allBooks;// 2 Beans

    @Autowired
    @Qualifier
    private Collection<Book> qualifierBooks; // 2 Beans = book1+book2 -> 4 Beans = book1 + boo2 +book3 + book4

    @Autowired
    @BookGroup
    private Collection<Book> groupedBooks; // 2 Beans = book3 + book4
    @Bean
    @Qualifier
    public Book book1(){
        return createBook(1);
    }

    @Bean
    @Qualifier
    public Book book2(){
        return createBook(2);
    }

    @Bean
    @BookGroup
    public Book book3(){
        return createBook(3);
    }

    @Bean
    @BookGroup
    public Book book4(){
        return createBook(4);
    }

    private static Book createBook(int price){
        Book book = new Book();
        book.setPrice(price);
        return book;
    }
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class（配置类）
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        //启动Spring 应用上下文
        applicationContext.refresh();

        //依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        // 期待输出cheapBook Bean
        System.out.println("demo.book:"+demo.book);
        // 期待输出book Bean
        System.out.println("demo.saleBook:"+demo.saleBook);

        // 期待输出cheapBook Book Bean
        System.out.println("demo.allBooks:"+demo.allBooks);
        // 期待输出book1 book2 Bean -> book3 book4
        System.out.println("demo.qualifierBooks:"+demo.qualifierBooks);
        // 期待输出book3 book4 Bean
        System.out.println("demo.groupedBooks:"+demo.groupedBooks);
        //显示关闭Spring 应用上下文
        applicationContext.close();
    }


}
