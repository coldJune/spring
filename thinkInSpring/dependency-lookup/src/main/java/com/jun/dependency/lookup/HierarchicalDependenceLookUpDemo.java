package com.jun.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找示例
 * @Author dengxiaojun
 * @Date 2020/9/3 21:45
 * @Version 1.0
 */
public class HierarchicalDependenceLookUpDemo {//@Configuration是非必须注解
    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类ObjectProviderDemo 作为配置类(Configuration Class)
        applicationContext.register(HierarchicalDependenceLookUpDemo.class);

        //1. 获取HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前BeanFactory的Parent BeanFactory:"+ beanFactory.getParentBeanFactory());
        // 2. 设置Parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前BeanFactory的Parent BeanFactory:"+ beanFactory.getParentBeanFactory());
//        displayContainsLocalBean(beanFactory,"book");//containsBean当前BeanFactory未找到会查找parentBeanFactory,containsLocalBean不会
//        displayContainsLocalBean(parentBeanFactory,"book");

        displayContainsBean(beanFactory,"book");//containsBean当前BeanFactory未找到会查找parentBeanFactory,containsLocalBean不会
        displayContainsBean(parentBeanFactory,"book");

        //启动应用上下文
        applicationContext.refresh();
        //依赖查找集合对象
        //关闭应用上下文
        applicationContext.close();
    }

    /**
     * 递归查找ParentBeanFactory,类似双亲委托模式
     * 等同于containsBean
     * @param beanFactory
     * @param beanName
     */
    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName){
        System.out.printf("当前BeanFactory[%s]是否包含 Bean[name: %s]: %s\n",beanFactory, beanName,
                containsBean(beanFactory,beanName));
    }

    private static  boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName){
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if(containsBean(parentHierarchicalBeanFactory, beanName)){
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }
    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName){
        System.out.printf("当前BeanFactory[%s]是否包含 LocalBean[name: %s]: %s\n",beanFactory, beanName,
                beanFactory.containsLocalBean(beanName));

    }
    private static ConfigurableListableBeanFactory createParentBeanFactory(){
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML 配置文件 classPath路径
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }

}
