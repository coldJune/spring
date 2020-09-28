package com.jun.dependency.injection.setter;

import com.jun.dependency.injection.BookHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于API 资源的依赖Setter方法注入示例
 * @Author dengxiaojun
 * @Date 2020/9/24 10:42
 * @Version 1.0
 */
public class ApiDependencySetterInjectionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //生成BookHolder的BeanDefinition
        BeanDefinition bookHolderBeanDefinition = createBookHolderBeanDefinition();
        //注册BookHolder的BeanDefinition
        applicationContext.registerBeanDefinition("bookHolder",bookHolderBeanDefinition);
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

    /**
     * 为{@link BookHolder}生成{@link BeanDefinition}
     * @return
     */
    private static BeanDefinition createBookHolderBeanDefinition(){
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(BookHolder.class);
        definitionBuilder.addPropertyReference("book","book");
        return definitionBuilder.getBeanDefinition();
    }

//    @Bean
//    public BookHolder bookHolder(Book book){ //cheapBook -> book primary=true
//
////        return new BookHolder(book);
//        BookHolder bookHolder = new BookHolder();
//        bookHolder.setBook(book);
//        return bookHolder;
//    }
}
