import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(MagicExistsCondition.class)
public class MagicConfig {
    @Bean
    public MagicBean magicBean(){
        return new MagicBean();
    }

}
