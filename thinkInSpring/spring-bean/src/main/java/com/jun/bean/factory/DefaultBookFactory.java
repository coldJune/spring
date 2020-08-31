package com.jun.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认{@link BookFactory} 实现
 * @Author dengxiaojun
 * @Date 2020/8/29 21:11
 * @Version 1.0
 */
public class DefaultBookFactory implements BookFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void initBookFactoryByPostConstruct(){
        System.out.println("initBookFactoryByPostConstruct: UserFactory初始化中...");
    }

    public void initBookFactoryByBean(){
        System.out.println("initBookFactoryByBean: UserFactory初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet: UserFactory初始化中...");

    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("preDestroy: UserFactory销毁中...");

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy(): UserFactory销毁中中...");

    }

    public void doDestroy(){
        System.out.println("自定义销毁方法doDestroy(): UserFactory销毁中中...");

    }
    @Override
    protected void finalize() throws Throwable{
        System.out.println("GC执行中");
    }
}
