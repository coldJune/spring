import com.jun.spitter.config.JdbcConfig;
import com.jun.spitter.model.Spitter;
import com.jun.spitter.repository.jdbc.JdbcSpitterRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcConfig.class)
public class JdbcTest {
    @Autowired
    private JdbcSpitterRepository jdbcSpitterRepository;
    private static Spitter[] SPITTERS = new Spitter[6];
    @BeforeClass
    public static void before() {
        SPITTERS[0] = new Spitter(1L, "habuma", "password", "Craig Walls",
                "craig@habuma.com", false);
        SPITTERS[1] = new Spitter(2L, "mwalls", "password", "Michael Walls",
                "mwalls@habuma.com", true);
        SPITTERS[2] = new Spitter(3L, "chuck", "password", "Chuck Wagon",
                "chuck@habuma.com", false);
        SPITTERS[3] = new Spitter(4L, "artnames", "password", "Art Names",
                "art@habuma.com", true);
        SPITTERS[4] = new Spitter(5l,"new","new","new","new",false);;
        SPITTERS[5] = new Spitter(4L, "arthur", "letmein", "Arthur Names",
                "arthur@habuma.com", false);
    }
    private static void assertSpitter(int expectedSpitterIndex, Spitter actual) {
        assertSpitter(expectedSpitterIndex, actual, "Newbie");
    }

    private static void assertSpitter(int expectedSpitterIndex, Spitter actual,
                                      String expectedStatus) {
        Spitter expected = SPITTERS[expectedSpitterIndex];
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getFullName(), actual.getFullName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.isUpdateByEmail(), actual.isUpdateByEmail());
    }

    @Test
    @Transactional
    public void findByUsername(){
        assertSpitter(0,jdbcSpitterRepository.findByUsername("habuma"));
        assertSpitter(1,jdbcSpitterRepository.findByUsername("mwalls"));
        assertSpitter(2,jdbcSpitterRepository.findByUsername("chuck"));
        assertSpitter(3,jdbcSpitterRepository.findByUsername("artnames"));

    }

    @Test
    @Transactional
    public void save(){
        Spitter spitter = new Spitter(null,"new","new","new","new",false);
        Spitter saved = jdbcSpitterRepository.save(spitter);
        assertSpitter(4,saved);
        spitter = new Spitter(4L, "artnames", "password", "Art Names",
                "art@habuma.com", true);
        saved = jdbcSpitterRepository.save(spitter);
        assertSpitter(3,saved);
    }
}
