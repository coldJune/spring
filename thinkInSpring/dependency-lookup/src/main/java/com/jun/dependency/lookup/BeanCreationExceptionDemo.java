package com.jun.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link BeanCreationException} 示例代码
 * @Author dengxiaojun
 * @Date 2020/9/3 21:45
 * @Version 1.0
 */
public class BeanCreationExceptionDemo {//@Configuration是非必须注解
    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册BeanDefinition Bean Class 是一个POJO 普通具体类
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("errorBean",beanDefinitionBuilder.getBeanDefinition());
        //启动应用上下文
        applicationContext.refresh();

        //关闭应用上下文
        applicationContext.close();
    }

    static class POJO implements InitializingBean{
        //PostConstruct会先执行,所以只会执行一次
        @PostConstruct
        public void init() throws Throwable{
            throw  new Throwable("PostConstruct: for purpose");

        }
        @Override
        public void afterPropertiesSet() throws Exception {
            throw  new Exception("afterPropertiesSet: for purpose");
        }
    }
}
