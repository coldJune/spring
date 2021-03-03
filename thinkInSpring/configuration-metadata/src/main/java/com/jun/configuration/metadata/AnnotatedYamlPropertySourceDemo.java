package com.jun.configuration.metadata;

import com.jun.ioc.overview.domain.Book;
import com.jun.ioc.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 基于 Java 注解 的 YAML 外部化配置示例
 * @author: dengxiaojun-001
 * @date 2021/3/3 15:20
 * Description:
 */
@PropertySource(name = "yamlPropertySource",
        value = "classpath:/META-INF/book.yaml",
        factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {
    @Bean
    public Book book(@Value("${book.price}") Integer price,
                     @Value("${book.name}") String name,
                     @Value("${book.city}") City city){
        Book book = new Book();
        book.setPrice(price);
        book.setName(name);
        book.setCity(city);
        return book;

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        configApplicationContext.register(AnnotatedYamlPropertySourceDemo.class);

        // 启动 Spring 上下文
        configApplicationContext.refresh();

        // 获取Map Yaml对象
        Book book = configApplicationContext.getBean("book",Book.class);
        System.out.println(book);
        // 关闭 Spring 上下文
        configApplicationContext.close();
    }
}
