package com.example.homework_50.dto;

import com.example.homework_50.entity.Comment;
import com.example.homework_50.entity.Publication;
import lombok.*;

import java.util.Date;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CommentDto {
    public static CommentDto from (Comment comment){
        return builder()
                .publication_id(comment.getPublication_id())
                .author(comment.getAuthor())
                .text(comment.getText())
                .timeOfComment(comment.getTimeOfComment())
                .build();
    }
    private Long publication_id; //FK for Publication
    private String author;
    private String text;
    private Date timeOfComment;
}
