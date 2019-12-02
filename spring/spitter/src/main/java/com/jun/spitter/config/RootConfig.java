package com.jun.spitter.config;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 配置其它组件
 */
@Configuration
@ComponentScan(basePackages = {"com.jun.spitter"}, excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
})
public class RootConfig {
}
