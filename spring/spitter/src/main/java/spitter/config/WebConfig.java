package spitter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 配置Web组件
 */
@Configuration
@EnableWebMvc//启动Spring MVC
@ComponentScan("spitter.web")//启动组件扫描
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置JSP解析视图
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver= new InternalResourceViewResolver();
        resolver.setPrefix("/src/main/views");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();//静态资源转发到默认的Servlet上
    }
}
