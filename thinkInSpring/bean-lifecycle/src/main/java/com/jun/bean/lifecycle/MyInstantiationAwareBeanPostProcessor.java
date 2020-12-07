package com.jun.bean.lifecycle;

import com.jun.ioc.overview.domain.Book;
import com.jun.ioc.overview.domain.CheapBook;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class MyInstantiationAwareBeanPostProcessor
        implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("cheapBook", beanName)
                && CheapBook.class.equals(beanClass)) {
            // 将配置完成的CheapBook 覆盖
            return new CheapBook();
        } else {
            return null; // 保持Spring IoC的实例化操作
        }
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("book", beanName)
                && Book.class.equals(bean.getClass())) {
            // "boook"对象不允许属性赋值(填入)(配置元信息->属性值)
            Book book = (Book) bean;
            book.setName("coldjune_");
            return false;
        }
        return true;
    }

    // book 是跳过 Bean 属性赋值填入
    // cheapBook 是完全跳过 Bean 实例化(Bean 属性赋值填入)
    // bookHolder
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 对“bookHolder” Bean进行拦截
        if (ObjectUtils.nullSafeEquals("bookHolder", beanName)
                && BookHolder.class.equals(bean.getClass())) {
            // 假设 <property name="number" value="1"/> 配置的话，那么在PropertyValues就包含一个 PropertyValue(number=1)
            final MutablePropertyValues mutablePropertyValues;
            if (pvs instanceof MutablePropertyValues) {
                mutablePropertyValues = (MutablePropertyValues) pvs;
            } else {
                mutablePropertyValues = new MutablePropertyValues();
            }
            // 等价于 <property name="number" value="1"/>
            mutablePropertyValues.addPropertyValue("number", "1");
            // 如果存在“desc”的字段配置 则把值进行替换
            if (mutablePropertyValues.contains("desc")) {
                // 原始配置 <property name="desc" value="the bookHolder v1"/>
                // MutablePropertyValues 是不可变的 即不能直接替换
                mutablePropertyValues.removePropertyValue("desc");
                mutablePropertyValues.addPropertyValue("desc", "the bookHolder v2");
            }
            return mutablePropertyValues;
        }

        return null;
    }
}
