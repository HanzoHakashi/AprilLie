package com.example.homework_50.dao;

import com.example.homework_50.entity.Publication;
import com.example.homework_50.entity.Subscriptions;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
@Component
public class SubscriptionDao extends BaseDao{
    public SubscriptionDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists subscriptions\n" +
                "(\n" +
                "    id    bigserial primary key," +
                "    userID bigserial ,\n" +
                "    foreign key (userID) references usr (id),\n" +
                "    subscriber  varchar not null,\n" +
                "    subscription  varchar not null,\n" +
                "    dateOfEvent  DATE not null\n" +
                ");");
    }
    public List<Subscriptions> findAll() {
        String sql = "select * from subscriptions";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Subscriptions.class));
    }
    public void save(Subscriptions subscriptions) {
        String sql = "insert into subscriptions(userEmail,subscriber,subscription,dateOfEvent) " +
                "values(?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subscriptions.getUserEmail());
            ps.setString(2, subscriptions.getSubscriber());
            ps.setString(3, subscriptions.getSubscription());
            ps.setDate(4, (Date) subscriptions.getDateOfEvent());
            return ps;
        });
    }
    public void deleteAll() {
        String sql = "delete from subscriptions";
        jdbcTemplate.update(sql);
    }
}
