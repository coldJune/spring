package com.jun.dependency.injection.annotaion;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * 自定义依赖注入注解
 * @Author dengxiaojun
 * @Date 2020/10/24 12:26
 * @Version 1.0
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedBook {
}
