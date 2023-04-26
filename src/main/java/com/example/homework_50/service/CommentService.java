package com.example.homework_50.service;

import com.example.homework_50.dao.CommentDao;
import com.example.homework_50.entity.Comment;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class CommentService {
    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @ModelAttribute("email")
    public String getUsername(Authentication authentication) {
        return authentication.name();
    }

    public void addComment(Long publication_id, String author, String desc){
        Comment comment = new Comment();
        comment.setPublication_id(publication_id);
        comment.setAuthor(author);
        comment.setText(desc);
        comment.setTimeOfComment(Date.valueOf(LocalDate.now()));
        commentDao.save(comment);
    }
}
