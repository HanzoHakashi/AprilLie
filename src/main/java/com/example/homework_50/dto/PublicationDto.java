package com.example.homework_50.dto;

import com.example.homework_50.entity.Publication;
import lombok.*;

import java.util.Date;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDto {
    public static PublicationDto from (Publication publication){
        return builder()
                .id(publication.getId())
                .image(publication.getImage())
                .description(publication.getDescription())
                .postDate(java.sql.Date.valueOf(publication.getPostDate()))
                .ownerEmail(publication.getOwnerEmail())
                .build();
    }

    private Long id;
    private String image;
    private String description;
    private Date postDate;
    private String ownerEmail;
}
