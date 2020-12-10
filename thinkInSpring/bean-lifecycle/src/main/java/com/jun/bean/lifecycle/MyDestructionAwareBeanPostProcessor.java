package com.jun.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * {@link DestructionAwareBeanPostProcessor} 实现
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("bookHolder", beanName)
                && BookHolder.class.equals(bean.getClass())) {
            BookHolder bookHolder = (BookHolder) bean;
            // BookHolder desc="BookHolder the bookHolder v8"
            System.out.println("before postProcessBeforeDestruction, bookHolder.desc="+bookHolder.getDesc());
            bookHolder.setDesc("the bookHolder v9");
        }
    }
}
