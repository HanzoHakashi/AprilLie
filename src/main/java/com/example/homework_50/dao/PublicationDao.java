package com.example.homework_50.dao;

import com.example.homework_50.entity.Publication;
import com.example.homework_50.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//первое задание
@Component
public class PublicationDao extends BaseDao{


    public PublicationDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists publication\n" +
                "(\n" +
                "    id       bigserial primary key,\n" +
                "    image     varchar,\n" +
                "    description  varchar,\n" +
                "    postDate DATE not null," +
                "    ownerId bigserial ,\n" +
                "    foreign key (ownerId) references usr (id)\n" +
                ");");
    }
    public List<Publication> findAll() {
        String sql = "select * from publication";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    public Optional<Publication> findPostByUser(String email) {
        String sql = "select * " +
                "from publication " +
                "where ownerEmail = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class), email)
        ));
    }
    public List<Publication> findPostBySubscriptions(String owner){
        String sql = "select * " +
                "from publication" +
                "where ownerEmail in(" +
                "select subscription " +
                "from subscriptions where userEmail = ?)";
       return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Publication.class),owner);
    }

    public void save(Publication publication) {
        String sql = "insert into publication(image, description, postDate,ownerEmail) " +
                "values(?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, publication.getImage());
            ps.setString(2, publication.getDescription());
            ps.setDate(3, Date.valueOf(publication.getPostDate()));
            ps.setString(4,publication.getOwnerEmail());
            return ps;
        });
    }

    public void deletePublication(Long publicationID, String email) {
        String deletePublication = "DELETE FROM publications WHERE id = ? AND ownerEmail = ?";
//        String deleteComments = "DELETE FROM comments WHERE publication_id = ?";

//        jdbcTemplate.update(deleteComments, publicationID);
        jdbcTemplate.update(deletePublication, publicationID, email);
    }


    public void deleteAll() {
        String sql = "delete from publication";
        jdbcTemplate.update(sql);
    }
}
