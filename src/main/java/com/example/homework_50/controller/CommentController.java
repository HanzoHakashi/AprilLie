package com.example.homework_50.controller;

import com.example.homework_50.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    public final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("add/{text}")
    public void addComment(@PathVariable String text, @RequestParam Long publication_id,@RequestParam String author){
        commentService.addComment(publication_id,author,text);
    }
}
