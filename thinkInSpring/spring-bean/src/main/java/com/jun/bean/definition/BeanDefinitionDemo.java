package com.jun.bean.definition;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**{@link org.springframework.beans.factory.config.BeanDefinition}构建示例
 * @Author dengxiaojun
 * @Date 2020/7/29 21:32
 * @Version 1.0
 */
public class BeanDefinitionDemo {
    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Book.class);
        //通过属性设置
        beanDefinitionBuilder.addPropertyValue("price",23);
        beanDefinitionBuilder.addPropertyValue("name","spring");
        //获取实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //beanDefinition 并非Bean的最终状态，可以自定义修改
        System.out.println(beanDefinition.getBeanClassName());
        //2.通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置Bean类型
        genericBeanDefinition.setBeanClass(Book.class);
        //通过MutablePropertyValues批量操作属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue("price",23);
//        propertyValues.addPropertyValue("name","spring");
        propertyValues
                .add("price",23)
                .add("name","spring");
        genericBeanDefinition.setPropertyValues(propertyValues);
        System.out.println(genericBeanDefinition.getPropertyValues());
    }
}
