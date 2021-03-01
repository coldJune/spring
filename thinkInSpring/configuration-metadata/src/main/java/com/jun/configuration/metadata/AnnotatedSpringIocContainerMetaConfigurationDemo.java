package com.jun.configuration.metadata;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * 基于Java注解 Spring IoC 容器元信息配置示例
 * @author: dengxiaojun-001
 * @date 2021/3/1 14:25
 * Description:
 */
// 将当前类作为 Configuration Class
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(Book.class)
@PropertySource("classpath:/META-INF/book-bean-definitions.properties") // Java 8  @Repeatable 支持
@PropertySource("classpath:/META-INF/book-bean-definitions.properties")
public class AnnotatedSpringIocContainerMetaConfigurationDemo {

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
        context.register(AnnotatedSpringIocContainerMetaConfigurationDemo.class);
        // 启动应用上下文
        context.refresh();
        Map<String, Book> bookMap = context.getBeansOfType(Book.class);
        bookMap.entrySet().stream().forEach(bookEntry ->{
            System.out.printf("Book bean name:%s, content:%s\n", bookEntry.getKey(),bookEntry.getValue());
        });
        // 关闭应用上下文
        context.close();
    }
}
