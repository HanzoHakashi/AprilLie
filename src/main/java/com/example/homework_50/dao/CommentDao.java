package com.example.homework_50.dao;

import com.example.homework_50.entity.Comment;
import com.example.homework_50.entity.Publication;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.Date;
import java.sql.PreparedStatement;
@Component
public class CommentDao extends BaseDao{
    public CommentDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists comments\n" +
                "(\n" +
                "    publication_id    bigserial primary key,\n" +
                "    foreign key (publication_id) references publication (id),\n" +
                "    author bigserial,\n" +
                "    foreign key (author) references usr (id),\n" +
                "    text  varchar not null,\n" +
                "    timeOfComment  DATE not null\n" +
                ");");
    }
    public void deleteAll() {
        String sql = "delete from comments";
        jdbcTemplate.update(sql);
    }
    public void save(Comment comment) {
        String sql = "insert into comments(publication_id,author, text,timeOfComment) " +
                "values(?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1,comment.getPublication_id());
            ps.setString(2, comment.getAuthor());
            ps.setString(3, comment.getText());
            ps.setDate(4,(Date) comment.getTimeOfComment());
            return ps;
        });
    }


}
