package com.jun.dependency.injection.annotaion;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 书组注解，扩展{@link Qualifier}
 * @Author dengxiaojun
 * @Date 2020/10/14 20:44
 * @Version 1.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface BookGroup {
}
