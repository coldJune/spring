package com.jun.spitter.repository.jdbc;

import com.jun.spitter.model.Spitter;
import com.jun.spitter.model.Spittle;
import com.jun.spitter.repository.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcSpittleRepository implements SpittleRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String SELECT_SPITTLE = "select sp.id, s.id as spitterId, s.username, s.password, s.fullname, s.email, s.updateByEmail, sp.message, sp.postedTime from Spittle sp, Spitter s where sp.spitter = s.id";
    private static final String SELECT_SPITTLE_BY_ID = SELECT_SPITTLE + " and sp.id=?";

    public List<Spittle> findSpittles(long max, int count) {
        return null;
    }

    public Spittle findOne(Long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SPITTLE_BY_ID, new SpittleRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Spittle save(Spittle spittle) {
        long spittleId = insertSpittleAndReturnId(spittle);
        return new Spittle(spittleId, spittle.getSpitter(), spittle.getMessage(), spittle.getPostedTime());
    }

    private long insertSpittleAndReturnId(Spittle spittle) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("Spittle");
        jdbcInsert.setGeneratedKeyName("id");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("spitter", spittle.getSpitter().getId());
        args.put("message", spittle.getMessage());
        args.put("postedTime", spittle.getPostedTime());
        long spittleId = jdbcInsert.executeAndReturnKey(args).longValue();
        return spittleId;
    }
    private static final class SpittleRowMapper implements RowMapper<Spittle> {
        public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String message = rs.getString("message");
            Date postedTime = rs.getTimestamp("postedTime");
            long spitterId = rs.getLong("spitterId");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String fullName = rs.getString("fullname");
            String email = rs.getString("email");
            boolean updateByEmail = rs.getBoolean("updateByEmail");
            Spitter spitter = new Spitter(spitterId, username, password, fullName, email, updateByEmail);
            return new Spittle(id, spitter, message, postedTime);
        }
    }
}
