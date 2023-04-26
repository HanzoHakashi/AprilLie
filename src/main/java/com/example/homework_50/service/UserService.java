package com.example.homework_50.service;



import com.example.homework_50.dao.UserDao;
import com.example.homework_50.dto.UserDto;
import com.example.homework_50.entity.User;
import com.example.homework_50.exeption.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final UserDao userDao;
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public UserDto findByEmail(String email){
        var user = userDao.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the email: " + email));
        return UserDto.from(user);
    }

    public String getUserEmail(String email){
        Optional<User> userOptional = userDao.findByEmail(email);
        var client = userOptional
                .orElseThrow(()-> new ResourceNotFoundException("Не было найдено такого клиента"));
        if (client == null) {
            throw new RuntimeException("Client not found for email: " + email);
        }
        return client.getEmail();
    }

    public UserDto findByName(String name){
        var user = userDao.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the name: " + name));
        return UserDto.from(user);
    }

    public UserDto findByUserName(String username){
        var user = userDao.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the username: " + username));
        return UserDto.from(user);
    }

    public void createUser(String email, String name, String username, String password){
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        userDao.save(user);
    }
}
