import java.util.Properties;

public class MagicProperties {
    MagicProperties (){
        Properties properties = new Properties();
        properties.setProperty("magic","");
        System.setProperties(properties);
    }
}
