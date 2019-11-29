import config.Config;
import config.ConfigWithDefault;
import config.ConfigWithRequired;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import soundsystem.BlankDisc;

import static org.junit.Assert.*;

public class EnvTest {
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = Config.class)
    public static class TestFromProperties{
        @Autowired
        private BlankDisc blankDisc;
        @Test
        public void test(){
            assertEquals ("title", blankDisc.getTitle());
            assertEquals ("artist", blankDisc.getArtist());
        }

    }
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = ConfigWithDefault.class)
    public static class TesWithDefault{
        @Autowired
        private BlankDisc blankDisc;
        @Test
        public void test(){
            assertEquals ("title", blankDisc.getTitle());
            assertEquals ("artist", blankDisc.getArtist());
        }

    }
    public static class TesWithRequired{

        @Test
        public void test(){
        AnnotationConfigApplicationContext context  =  new AnnotationConfigApplicationContext(ConfigWithRequired.class);
        BlankDisc blankDisc = context.getBean(BlankDisc.class);
            assertNull (blankDisc.getTitle());
            assertNull ( blankDisc.getArtist());

        }

    }
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:config.xml")
    public static class TesWithXml{
        @Autowired
        private BlankDisc blankDisc;
        @Test
        public void test(){
            assertEquals ("title", blankDisc.getTitle());
            assertEquals ("artist", blankDisc.getArtist());
        }

    }

}
