package com.jun.bean.definition;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**注解BeanDefinition 示例
 * @Author dengxiaojun
 * @Date 2020/8/19 22:04
 * @Version 1.0
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)//通过@Import来进行导入
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class（配置类）
        applicationContext.register(Config.class);
        //1.命名Bean的注册方式
        registerBookBeanDefinition(applicationContext,"cold-book");
        //2. 非命名的注册方式
        registerBookBeanDefinition(applicationContext);

        //启动Spring应用上下文
        applicationContext.refresh();
        System.out.println("Config Beans的所有beans:"+applicationContext.getBeansOfType(Config.class));
        System.out.println("Book beans的所有beans:"+applicationContext.getBeansOfType(Book.class));
        //显式关闭Spring应用上下文
        applicationContext.close();
    }

    public static void registerBookBeanDefinition(BeanDefinitionRegistry registry,String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Book.class);
        beanDefinitionBuilder
                .addPropertyValue("price",1)
                .addPropertyValue("name","jun");
        //判断是否存在beanName
        if(StringUtils.hasText(beanName)){
            //命名的方式注册BeanDefinition
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }else{
            //非命名注册的方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }
    }
    public static void registerBookBeanDefinition(BeanDefinitionRegistry registry){
        registerBookBeanDefinition(registry,null);
    }

    //通过@Component方式
    @Component
    public static class Config{
        /**
         * 通过Java注解的方式，定义一个Bean
         * @return
         */
        //通过@Bean方式定义
        @Bean(name={"book","myBook"})
        public Book book(){
            Book book = new Book();
            book.setPrice(1);
            book.setName("book");
            return book;
        }
    }
}
