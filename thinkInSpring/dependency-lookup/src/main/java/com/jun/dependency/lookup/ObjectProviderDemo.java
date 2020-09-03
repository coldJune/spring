package com.jun.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过{@link org.springframework.beans.factory.ObjectProvider} 进行依赖查找
 * @Author dengxiaojun
 * @Date 2020/9/3 21:45
 * @Version 1.0
 */
public class ObjectProviderDemo {//@Configuration是非必须注解
    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类ObjectProviderDemo 作为配置类(Configuration Class)
        applicationContext.register(ObjectProviderDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找集合对象
        lookupByObjectProvider(applicationContext);
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public String helloWorld(){//当没有指定名称时， Bean的名称为方法名
        return "Hello,World";
    }
    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
