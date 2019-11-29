import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MagicConfig.class )
public class MagicBeanTest {
    @Autowired
    private ApplicationContext context;
    @Test
    public void shouldBeNull(){
        assertFalse(context.containsBean("magicBean"));
    }

    @Test
    public void shouldNotBeNull(){

        assertTrue(context.containsBean("magicBean"));
    }

    @BeforeClass
    public static void addMagicProperties(){
        System.setProperty("magic","");
    }
}
