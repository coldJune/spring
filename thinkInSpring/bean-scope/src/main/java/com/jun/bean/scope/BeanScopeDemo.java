package com.jun.bean.scope;

import com.jun.ioc.overview.domain.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;
import java.util.Random;

/** Bean 的作用域示例
 * @Author dengxiaojun
 * @Date 2020/11/5 21:22
 * @Version 1.0
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    // 默认 scope 就是 "singleton"
    public static Book singletonBook(){
        return createBook();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static Book prototypeBook(){
        return createBook();
    }


    @Autowired
    @Qualifier("singletonBook")
    private Book singletonBook1;

    @Autowired
    @Qualifier("singletonBook")
    private Book singletonBook2;

    @Autowired
    @Qualifier("prototypeBook")
    private Book prototypeBook1;

    @Autowired
    @Qualifier("prototypeBook")
    private Book prototypeBook2;

    @Autowired
    private Map<String, Book> books;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory; //Resolvable Dependency
    private static Book createBook(){
        Book book = new Book();
        book.setPrice(new Random().nextInt());
        return book;

    }
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class(配置类) -> Spring Bean
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean名称：%s 在初始化后回调...%n",bean.getClass().getSimpleName(),beanName);
                    return bean;
                }
            });
        });
        // 启动Spring 应用上下文
        applicationContext.refresh();

        // 结论一：
        // Singleton Bean无论依赖查找还是依赖注入 均为同一个对象
        // Prototype Bean无论依赖查找还是依赖注入 均为新生成的对象

        // 结论二：
        // 如果依赖注入集合类型的对象，Singleton Bean和Prototype Bean均会存在一个
        // Prototype Bean 有别于其他地方的依赖注入 Prototype Bean

        // 结论三：
        // 无论 Singleton 还是 Prototype Bean 均会执行初始化方法回调
        // 不过仅 Singleton Bean 会执行销毁方法回调
        scopedBeanByLookup(applicationContext);

        scopedBeanByInjection(applicationContext);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

    private static void scopedBeanByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("singletonBook1 = "+beanScopeDemo.singletonBook1);
        System.out.println("singletonBook2 = "+beanScopeDemo.singletonBook2);
        System.out.println("prototypeBook1 = "+beanScopeDemo.prototypeBook1);
        System.out.println("prototypeBook2 = "+beanScopeDemo.prototypeBook2);
        System.out.println("books = " + beanScopeDemo.books);

    }

    private static void scopedBeanByLookup(AnnotationConfigApplicationContext applicationContext) {
        for(int i = 0; i < 3; i++){
            // singletonBook 是共享的Bean对象
            Book singletonBook = applicationContext.getBean("singletonBook", Book.class);
            System.out.println("singletonBook = " +singletonBook);

            // prototypeBook 是每次依赖查找均生成新的Bean 对象
            Book prototypeBook = applicationContext.getBean("prototypeBook",Book.class);
            System.out.println("prototypeBook = " + prototypeBook);
        }
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("当前 BeanScopeDemo Bean 正在销毁中...");
        singletonBook1.destroy();
        prototypeBook1.destroy();
        prototypeBook2.destroy();
        // 获取 BeanDefinition
        for(Map.Entry<String, Book> entry:this.books.entrySet()){
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if(beanDefinition.isPrototype()){ // 如果当前 Bean是 prototype scope
                entry.getValue().destroy();
            }
        }
        System.out.println("当前 BeanScopeDemo Bean 销毁完成");

    }
}
