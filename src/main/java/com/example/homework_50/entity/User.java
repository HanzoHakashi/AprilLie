package com.example.homework_50.entity;

import com.example.homework_50.util.Generator;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String name;
    private String username;
    private String password;
    private int counter;
    private Boolean enabled = Boolean.TRUE;

}
