package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import soundsystem.BlankDisc;

@Configuration
@ComponentScan(basePackageClasses = BlankDisc.class)
@PropertySource("classpath:app.properties")
public class Config {
    @Autowired
    private Environment env;
    @Bean
    public BlankDisc blankDisc(){
        return new BlankDisc(
                env.getProperty("title"),
                env.getProperty("artist")
        );
    }
}
