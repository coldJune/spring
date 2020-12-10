package com.jun.bean.lifecycle;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author dengxiaojun
 * @Date 2020/12/2 21:42
 * @Version 1.0
 */
public class BookHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware
        , InitializingBean,SmartInitializingSingleton, DisposableBean {
    private Book book;

    private Integer number;
    private String desc;

    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;

    private Environment environment;
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    // 依赖于注解驱动
    @PostConstruct
    public void initPostConstruct(){
        System.out.println(" before initPostConstruct desc="+this.desc);
        // postProcessBeforeInitialization V3 -> initPostConstruct V4
        this.desc="the bookHolder v4";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" before afterPropertiesSet desc="+this.desc);
        // initPostConstruct V4 -> afterPropertiesSet V5
        this.desc="the bookHolder v5";
    }

    public void init(){
        System.out.println("before init desc="+this.desc);
        // afterPropertiesSet V4 -> init V6
        this.desc = "the bookHolder v6";
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("before afterSingletonsInstantiated desc="+this.desc);
        // postProcessAfterInitialization v7 -> afterSingletonsInstantiated V8
        this.desc = "the bookHolder v8";
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("before preDestroy desc="+this.desc);
        // postProcessBeforeDestruction v9 -> preDestroy V10
        this.desc = "the bookHolder v10";
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("before destroy desc="+this.desc);
        // destroy V10 -> destroy V11
        this.desc = "the bookHolder v11";
    }
    public void doDestroy(){
        System.out.println("before doDestroy desc="+this.desc);
        // destroy V11 -> doDestroy V12
        this.desc = "the bookHolder v12";
    }

    public BookHolder(Book book) {
        this.book = book;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BookHolder{" +
                "book=" + book +
                ", number=" + number +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("bookHolder 正在GC");
    }
}
