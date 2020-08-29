package com.jun.bean.definition;

import com.jun.ioc.overview.dependency.domain.Book;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**{@link BeanDefinition}构建示例
 * @Author dengxiaojun
 * @Date 2020/7/29 21:32
 * @Version 1.0
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        Book bookByStaticMethod = beanFactory.getBean("book-by-static-method",Book.class);
        Book bookByInstanceMethod = beanFactory.getBean("book-by-instance-method",Book.class);
        Book bookByFactoryBean = beanFactory.getBean("book-by-factory-bean",Book.class);
        System.out.println("静态方法实例化："+bookByStaticMethod);
        System.out.println("实例(Bean)方法实例化："+bookByInstanceMethod);
        System.out.println("FactoryBean实例化:"+bookByFactoryBean);
        System.out.println(bookByInstanceMethod==bookByStaticMethod);
        System.out.println(bookByInstanceMethod==bookByFactoryBean);
    }
}
