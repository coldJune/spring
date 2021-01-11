package com.jun.configuration.metadata;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ObjectUtils;

/**
 * Bean 配置元信息示例
 * @author: dengxiaojun-001
 * @date 2021/1/11 14:48
 * Description:
 */
public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        // BeanDefinition 的定义(声明)
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Book.class);
        beanDefinitionBuilder.addPropertyValue("name","ThinkInSpring");
        // 获取AbstractBeanDefinition
        AbstractBeanDefinition abstractBeanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 附加属性（不影响Bean实例化、属性赋值、初始化即Bean populate、initialize）
        abstractBeanDefinition.setAttribute("name","JavaSpring");
        // 当前BeanDefinition来自于何方（辅助作用）
        abstractBeanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if(ObjectUtils.nullSafeEquals("book",beanName) && Book.class.equals(bean.getClass())){
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    if(BeanConfigurationMetadataDemo.class.equals(bd.getSource())){
                        // 属性（存储）上下文
                        String name = (String)bd.getAttribute("name"); // 就是 "JavaSpring"
                        System.out.println("name="+name);
                        Book book = (Book)bean;
                        book.setName(name);
                    }

                }
                return bean;
            }
        });
        // 注册Book 的BeanDefinition
        beanFactory.registerBeanDefinition("book",abstractBeanDefinition);

        Book book = beanFactory.getBean("book",Book.class);
        System.out.println(book);
    }
}
