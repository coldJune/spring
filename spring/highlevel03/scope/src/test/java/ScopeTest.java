import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class ScopeTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void testProxyScope(){
        Notedpad notedpad1 = context.getBean(Notedpad.class);
        Notedpad notedpad2 = context.getBean(Notedpad.class);
        assertNotSame(notedpad1, notedpad2);
    }

    @Test
    public void testSingle(){
        Single single1 = context.getBean(Single.class);
        Single single2 = context.getBean(Single.class);
        assertSame(single1, single2);
    }
}
