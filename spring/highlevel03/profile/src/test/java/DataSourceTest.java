import javafx.scene.chart.PieChart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import javax.swing.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DataSourceConfig.class)
@ContextConfiguration("classpath:profile.xml")
@ActiveProfiles("dev")
public class DataSourceTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    public void embeddedDataSource(){
        assertNotNull(dataSource);
        List<String> results = jdbcTemplate.query("select id, name from Things", new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getLong("id")+":"+resultSet.getString("name");
            }
        });
        assertEquals(1,results.size());
        assertEquals("1:A",results.get(0));
    }
}




