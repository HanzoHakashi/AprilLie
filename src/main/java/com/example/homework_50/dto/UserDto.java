package com.example.homework_50.dto;

import com.example.homework_50.entity.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDto {

    public static UserDto from (User user){
        return builder()
                .email(user.getEmail())
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .counter(user.getCounter())
                .build();
    }

    private String email;
    private String name;
    private String username;
    private String password;
    private int counter;
}
