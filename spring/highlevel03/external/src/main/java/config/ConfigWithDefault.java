package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import soundsystem.BlankDisc;

@Configuration
public class ConfigWithDefault {
    @Autowired
    private Environment env;
    @Bean
    public BlankDisc blankDisc(){
        return new BlankDisc(
                env.getProperty("title","title"),
                env.getProperty("artist","artist")
        );
    }
}
