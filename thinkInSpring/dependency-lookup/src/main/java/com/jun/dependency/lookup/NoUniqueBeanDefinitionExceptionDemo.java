package com.jun.dependency.lookup;

import com.jun.ioc.overview.dependency.domain.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException} 示例代码
 * @Author dengxiaojun
 * @Date 2020/9/3 21:45
 * @Version 1.0
 */
public class NoUniqueBeanDefinitionExceptionDemo {//@Configuration是非必须注解
    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //NoUniqueBeanDefinitionExceptionDemo 作为配置类(Configuration Class)
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        try {
            applicationContext.getBean(String.class);
        }catch (NoUniqueBeanDefinitionException e){
            System.err.printf("Spring 应用上下文中存在%d个%s类型的Bean，具体原因：%s%n",
                    e.getNumberOfBeansFound(),String.class.getName(),e.getMessage());
        }
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public String bean1(){
        return "1";
    }

    @Bean
    public String bean2(){
        return "2";
    }
    @Bean
    public String bean3(){
        return "3";
    }

}
