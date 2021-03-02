package com.jun.configuration.metadata;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于Java注解 Spring IoC 容器元信息配置示例
 * @author: dengxiaojun-001
 * @date 2021/3/1 14:25
 * Description:
 */
// 将当前类作为 Configuration Class
@PropertySource("classpath:/META-INF/book-bean-definitions.properties")
public class PropertySourceDemo {

    @Bean
    public Book configuredBook(@Value("${book.price}") Integer price,
                               @Value("${book.name}") String name){
        Book book = new Book();
        book.setPrice(price);
        book.setName(name);
        return book;

    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext();

        // 注册当前类作为Configuration Class
        context.register(PropertySourceDemo.class);
        // 启动应用上下文
        // 扩展 Environment 中的PropertySources
        // 添加 PropertySource操作中必须在refresh方法之前完成
        Map<String,Object> propertiesSource = new HashMap<>();
        propertiesSource.put("book.price",2);
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-property-source",propertiesSource);
        context.getEnvironment().getPropertySources().addFirst(propertySource);
        context.refresh();
        Map<String, Book> bookMap = context.getBeansOfType(Book.class);
        bookMap.entrySet().stream().forEach(bookEntry ->{
            System.out.printf("Book bean name:%s, content:%s\n", bookEntry.getKey(),bookEntry.getValue());
        });
        System.out.println(context.getEnvironment().getPropertySources());
        // 关闭应用上下文
        context.close();
    }
}
