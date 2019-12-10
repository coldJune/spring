

import com.jun.spitter.config.CacheConfig;
import com.jun.spitter.model.Spitter;
import com.jun.spitter.model.Spittle;
import com.jun.spitter.repository.SpittleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("cache")
@ContextConfiguration(classes = CacheConfig.class)
public class CacheTest {
    @Autowired
    SpittleRepository spittleRepository;

    @Test
    public void testFindOne(){
        Spittle spittle1 = spittleRepository.findOne(1l);
        assertEquals(1,spittle1.getId().longValue());
        assertEquals("This is a test spittle message", spittle1.getMessage());
        assertEquals(1332683100000l, spittle1.getPostedTime().getTime());
        assertEquals(1, spittle1.getSpitter().getId().longValue());
        assertEquals("habuma", spittle1.getSpitter().getUsername());
        assertEquals("password", spittle1.getSpitter().getPassword());
        assertEquals("Craig Walls", spittle1.getSpitter().getFullName());
        assertEquals("craig@habuma.com", spittle1.getSpitter().getEmail());
        assertFalse(spittle1.getSpitter().isUpdateByEmail());
    }

    @Test
    @Transactional
    public void testSave(){
        Spitter spitter = spittleRepository.findOne(1l).getSpitter();
        Spittle spittle = new Spittle(null, spitter, "Un Nuevo Spittle from Art", new Date());
        Spittle saved = spittleRepository.save(spittle);
        assertEquals(16,saved.getId().longValue());
    }
}
