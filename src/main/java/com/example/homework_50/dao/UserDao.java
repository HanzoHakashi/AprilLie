package com.example.homework_50.dao;

import com.example.homework_50.entity.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component
public class UserDao extends BaseDao{

    private final PasswordEncoder passwordEncoder;

    public UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, PasswordEncoder passwordEncoder) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists usr\n" +
                "(\n" +
                "    id    bigserial primary key," +
                "    email varchar,\n" +
                "    name  varchar not null,\n" +
                "    username  varchar not null,\n" +
                "    enabled BOOLEAN,\n" +
                "    password  varchar not null,\n" +
                "    counter  int default 0,\n" +
                "    role  varchar\n" +
                ");");
    }

    public Optional<User> findByEmail(String email) {
        String sql = "select * " +
                "from usr " +
                "where email = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email)
        ));
    }
    public Optional<User> findByName(String name) {
        String sql = "select * " +
                "from usr " +
                "where name = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), name)
        ));
    }
    public Optional<User> findByUserName(String username) {
        String sql = "select * " +
                "from usr " +
                "where username = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username)
        ));
    }

    public void deleteAll() {
        String sql = "delete from usr";
        jdbcTemplate.update(sql);
    }

//    public void saveAll(User user) {
//        String sql = "insert into usr(email, name,username, password,counter) " +
//                "values(?,?,?,?,?)";
//
//        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setString(1, user.getEmail());
//                ps.setString(2, users.get(i).getName());
//                ps.setString(3, users.get(i).getUsername());
//                ps.setString(4,users.get(i).getPassword());
//                ps.setInt(5,users.get(i).getCounter());
//            }
//
//            public int getBatchSize() {
//                return users.size();
//            }
//        });
//    }

    public void save(User user) {
        String sql = "insert into usr(email,name,username,enabled,password,counter) " +
                "values(?,?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUsername());
            ps.setBoolean(4,user.getEnabled());
            ps.setString(5, this.passwordEncoder.encode(user.getPassword()));
            ps.setInt(6, user.getCounter());
            return ps;
        });
    }
}
