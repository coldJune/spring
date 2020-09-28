package com.jun.ioc.overview.container;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/** {@link BeanFactory}作为IoC  容器示例
 * @Author dengxiaojun
 * @Date 2020/7/27 22:26
 * @Version 1.0
 */
public class BeanFactoryAsIoCContainer {
    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML 配置文件 classPath路径
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean定义加载数量："+beanDefinitionsCount);
        //依赖查找集合对象
        lookupCollectionType(beanFactory);
    }
    /**
     * 根据类型查找集合对象
     * @param beanFactory
     */
    private static void lookupCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, Book> books = listableBeanFactory.getBeansOfType(Book.class);
            System.out.println("查找到所有的Book对象："+books);
        }
    }
}
