package com.example.homework_50.controller;

import com.example.homework_50.dto.PublicationDto;
import com.example.homework_50.service.PublicationService;
import com.example.homework_50.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationService publicationService;
    private final UserService userService;

    public PublicationController(PublicationService publicationService, UserService userService) {
        this.publicationService = publicationService;
        this.userService = userService;
    }
    @GetMapping
    public List<PublicationDto> findAll(){
        return publicationService.findAll();
    }
    @GetMapping("/{email}")
    public PublicationDto getPublicationByID(@PathVariable String email){
        return publicationService.findPostByUser(email);
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @PostMapping("/submit-post")
    public ResponseEntity<?> submitPost(@RequestParam("image") String picture,@RequestParam("description")String desc, Authentication authentication){
        String email = userService.getUserEmail(authentication.getName());
        publicationService.addPublication(picture,desc,email);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/submit-post")
    public String getSubmitPostPage(){
        return "index";
    }

    @GetMapping("subscriptions/{autorSubscriptions}")
    public List<PublicationDto> getPublicationBySubscriptions(@PathVariable String autorsSubscriptions){
        return publicationService.findPostBySubscriptions(autorsSubscriptions);
    }

//    @PostMapping("/submit-post}")
//    public void addPublication(@PathVariable String description, @RequestParam String email, String image){
//        publicationService.addPublication(image,description,email);
//    }

    @DeleteMapping("delete/{publicationID}")
    public void deletePublication(@PathVariable Long publicationID,String email){
        publicationService.deletePublication(publicationID,email);
    }
}
