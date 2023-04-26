package com.example.homework_50.entity;

import com.example.homework_50.util.Generator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscriptions {
    private int id;
    private String userEmail;//email of account owner
    private String subscriber;//email of subscriber
    private String subscription;//email of subscriptions
    private Date dateOfEvent;

}
