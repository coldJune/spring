package com.jun.configuration.metadata;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于 XML 资源 的 YAML 外部化配置示例
 * @author: dengxiaojun-001
 * @date 2021/3/3 15:20
 * Description:
 */
public class XmlBasedYamlPropertySourceDemo {
    public static void main(String[] args) {
        // 创建 IoC 底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建XML资源的 BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 加载 XML 资源
        reader.loadBeanDefinitions("META-INF/yaml-property-source-context.xml");
        // 获取Map Yaml对象
        Map<String,Object> yamlMap = beanFactory.getBean("yamlMap",Map.class);
        System.out.println(yamlMap);
    }
}
