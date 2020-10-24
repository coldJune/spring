package com.jun.dependency.injection.resolution;

import com.jun.dependency.injection.annotaion.BookGroup;
import com.jun.dependency.injection.annotaion.InjectedBook;
import com.jun.dependency.injection.annotaion.MyAutowired;
import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 基于{@link Qualifier} 限定注入
 * @Author dengxiaojun
 * @Date 2020/9/24 10:42
 * @Version 1.0
 */
public class AnnotationDependencyInjectionResolutionDemo {
    @MyAutowired
    private Optional<Book> bookOptional; //cheapBook

    @InjectedBook
    private Book injectedBook; //cheapBook

//    @Bean(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        //替换原有注解过程 Autowired->InjectedBook
////        beanPostProcessor.setAutowiredAnnotationType(InjectedBook.class);
//        // 原有注解@Autowired+新注解MyAutowired InjectedBook
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(
//                Autowired.class,InjectedBook.class,MyAutowired.class
//        ));
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return beanPostProcessor;
//    }



    /**
     * 新增一个AutowiredAnnotationBeanPostProcessor， 自带的依然存在
     * 此时存在两个AutowiredAnnotationBeanPostProcessor
     * @return
     */
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor myBeanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        //替换原有注解过程 Autowired->InjectedBook
        beanPostProcessor.setAutowiredAnnotationType(InjectedBook.class);
        return beanPostProcessor;
    }
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class（配置类）
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载 XML 资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        //启动Spring 应用上下文
        applicationContext.refresh();

        //依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        // 期待输出cheapBook Bean
        System.out.println("demo.bookOptional:"+demo.bookOptional);

        // 期待输出cheapBook Bean
        System.out.println("demo.injectedBook:"+demo.injectedBook);
        //显示关闭Spring 应用上下文
        applicationContext.close();
    }


}
