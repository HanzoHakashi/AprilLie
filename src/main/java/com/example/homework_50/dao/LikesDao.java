package com.example.homework_50.dao;

import com.example.homework_50.entity.Comment;
import com.example.homework_50.entity.Likes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
@Component
public class LikesDao extends BaseDao{
    public LikesDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists likes\n" +
                "(\n" +
                "    likedPost bigserial not null,\n" +
                "    foreign key (likedPost) references publication (id),\n" +
                "    dateOfLike  DATE not null,\n" +
                "    likedUser varchar not null\n" +
                ");");
    }
    public void deleteAll() {
        String sql = "delete from likes";
        jdbcTemplate.update(sql);
    }

    public void save(Likes likes) {
        String sql = "insert into likes(likedPost, dateOfLike, likedUser) " +
                "values(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, likes.getLikedPost());
            ps.setDate(2, (Date) likes.getDateOfLike());
            ps.setString(3, likes.getLikedUser());
            return ps;
        });
    }
}
