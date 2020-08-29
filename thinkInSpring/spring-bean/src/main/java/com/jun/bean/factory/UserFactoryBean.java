package com.jun.bean.factory;

import com.jun.ioc.overview.dependency.domain.Book;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link Book} Bean 的{@link org.springframework.beans.factory.FactoryBean}实现
 * @Author dengxiaojun
 * @Date 2020/8/29 21:17
 * @Version 1.0
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return Book.createBook();
    }

    @Override
    public Class<?> getObjectType() {
        return Book.class;
    }
}
