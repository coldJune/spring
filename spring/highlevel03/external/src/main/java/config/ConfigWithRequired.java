package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import soundsystem.BlankDisc;

@Configuration
public class ConfigWithRequired {
    @Autowired
    private Environment env;
    @Bean
    public BlankDisc blankDisc(){
        //未配置properties会获取到null
        env.getProperty("title");
        return new BlankDisc(
                env.getProperty("title"),
                env.getProperty("artist")
        );
    }
}
