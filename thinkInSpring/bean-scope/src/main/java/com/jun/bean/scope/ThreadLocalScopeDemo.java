package com.jun.bean.scope;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;
import java.util.Random;

/**
 * ThreadLocalScope 示例
 * @Author dengxiaojun
 * @Date 2020/11/5 21:22
 * @Version 1.0
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public Book book(){
        return createBook();
    }
    private static Book createBook(){
        Book book = new Book();
        book.setPrice(new Random().nextInt());
        return book;

    }
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class(配置类) -> Spring Bean
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME,new ThreadLocalScope());
        });
        // 启动Spring 应用上下文
        applicationContext.refresh();
        scopedBeanByLookup(applicationContext);


        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

    private static void scopedBeanByLookup(AnnotationConfigApplicationContext applicationContext) {
        for(int i = 0;i<3;i++){
            Thread thread = new Thread(()->{
               Book book = applicationContext.getBean(Book.class);
               System.out.printf("threadId[%d][%s]%n",Thread.currentThread().getId(),book);
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {

            }
        }

        for(int i = 0;i<3;i++){
            Book book = applicationContext.getBean(Book.class);
            System.out.printf("threadId[%d][%s]%n",Thread.currentThread().getId(),book);
        }

    }


}
