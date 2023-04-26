package com.example.homework_50.entity;

import com.example.homework_50.dao.LikesDao;
import com.example.homework_50.util.Generator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
   private Long likedPost;//id of post
   private Date dateOfLike;
   private String likedUser;
}
