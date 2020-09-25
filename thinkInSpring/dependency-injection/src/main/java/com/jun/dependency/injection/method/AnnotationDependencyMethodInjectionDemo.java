package com.jun.dependency.injection.method;

import com.jun.dependency.injection.BookHolder;
import com.jun.ioc.overview.dependency.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解 资源的依赖方法注入示例
 * @Author dengxiaojun
 * @Date 2020/9/24 10:42
 * @Version 1.0
 */
public class AnnotationDependencyMethodInjectionDemo {
    private BookHolder bookHolder;

    private BookHolder bookHolder2;

    @Autowired
    public void init1(BookHolder bookHolder ){
        this.bookHolder = bookHolder;
    }
    @Resource
    public void init2(BookHolder bookHolder2){
        this.bookHolder2 = bookHolder2;
    }
    @Bean
    public BookHolder bookHolder(Book book){

//        return new BookHolder(book);
        BookHolder bookHolder = new BookHolder();
        bookHolder.setBook(book);
        return bookHolder;
    }
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class（配置类）
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        //启动Spring 应用上下文
        applicationContext.refresh();

        //依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        //@Autowired字段关联
        BookHolder bookHolder = demo.bookHolder;
        System.out.println(bookHolder);
        System.out.println(demo.bookHolder2);
        System.out.println(bookHolder == demo.bookHolder2);
        //显示关闭Spring 应用上下文
        applicationContext.close();
    }


}
