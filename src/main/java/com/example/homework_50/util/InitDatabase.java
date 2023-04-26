package com.example.homework_50.util;


import com.example.homework_50.dao.*;
import com.example.homework_50.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;
@AllArgsConstructor
@Configuration
public class InitDatabase {

    private static final Random r = new Random();
    private final PasswordEncoder encoder;



    @Bean
    CommandLineRunner init(UserDao userDao, PublicationDao publicationDao, CommentDao commentDao, LikesDao likesDao, SubscriptionDao subscriptionDao) {
        return (args) -> {

            userDao.createTable();
            subscriptionDao.createTable();
            publicationDao.createTable();
            likesDao.createTable();
            commentDao.createTable();

            commentDao.deleteAll();
            likesDao.deleteAll();
            publicationDao.deleteAll();
            subscriptionDao.deleteAll();
            userDao.deleteAll();

            User user1 = new User();
            user1.setUsername("SuperSaiyan");
            user1.setName("Son Goku");
            user1.setEmail("ssj@gmail.com");
            user1.setPassword("kamehameha213");
            user1.setCounter(0);
            userDao.save(user1);

            User user2 = new User();
            user2.setUsername("Black Swordsman");
            user2.setName("Guts");
            user2.setEmail("berserk@gmail.com");
            user2.setPassword("griffitmustdie");
            user2.setCounter(0);
            userDao.save(user2);
            User user3 = new User();
            user3.setUsername("test");
            user3.setName("test@test");
            user3.setEmail("test@mail.com");
            user3.setPassword("test");
            user3.setCounter(0);
            userDao.save(user3);

        };
    }

}
