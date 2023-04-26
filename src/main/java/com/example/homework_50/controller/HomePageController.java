package com.example.homework_50.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String showRegistrationPage() {
        return "registration";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
