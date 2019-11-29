import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:profile.xml")
@ActiveProfiles("prod")
public class ProductionDataSourceTest{
    @Autowired(required = false)
    private DataSource dataSource;
    @Test
    public void jndiTest(){
        assertNull(dataSource);
    }

}