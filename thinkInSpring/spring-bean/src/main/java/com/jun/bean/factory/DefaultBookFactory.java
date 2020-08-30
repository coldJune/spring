package com.jun.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
/**
 * 默认{@link BookFactory} 实现
 * @Author dengxiaojun
 * @Date 2020/8/29 21:11
 * @Version 1.0
 */
public class DefaultBookFactory implements BookFactory, InitializingBean {

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
}
