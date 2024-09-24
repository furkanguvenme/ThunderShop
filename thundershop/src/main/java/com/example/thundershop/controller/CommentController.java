package com.example.thundershop.controller;

import com.example.thundershop.dto.CommentDto;
import com.example.thundershop.entity.Comment;
import com.example.thundershop.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{userid}/products/{productid}")
    public CommentDto saveComment(@PathVariable(value = "userid") long userid, @PathVariable(value = "productid") long productid, @RequestBody Comment comment){
        Comment comment1 = commentService.saveComment(userid,productid,comment);
        return new CommentDto(comment1.getId(), comment1.getDescription());
    }

    @DeleteMapping("/{commentid}")
    public CommentDto deleteComment(@PathVariable long commentId){
        Comment comment = commentService.deleteComment(commentId);
        return new CommentDto(comment.getId(), comment.getDescription());
    }
}