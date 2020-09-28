package com.jun.ioc.overview.repository;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/** 书的信息仓库
 * @Author dengxiaojun
 * @Date 2020/7/26 21:36
 * @Version 1.0
 */
public class BookRepository {

    private BeanFactory beanFactory;//内建非Bean对象

    private Collection<Book> books;//自定义bean

    private ObjectFactory<Book> userObjectFactory;

    private ObjectFactory<ApplicationContext> applicationContextObjectFactory;
    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<Book> getUserObjectFactory() {
        return userObjectFactory;
    }

    public void setUserObjectFactory(ObjectFactory<Book> userObjectFactory) {
        this.userObjectFactory = userObjectFactory;
    }

    public ObjectFactory<ApplicationContext> getApplicationContextObjectFactory() {
        return applicationContextObjectFactory;
    }

    public void setApplicationContextObjectFactory(ObjectFactory<ApplicationContext> applicationContextObjectFactory) {
        this.applicationContextObjectFactory = applicationContextObjectFactory;
    }
}
