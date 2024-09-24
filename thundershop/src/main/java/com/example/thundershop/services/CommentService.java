package com.example.thundershop.services;

import com.example.thundershop.entity.Comment;

public interface CommentService {

    Comment saveComment(long userId, long productId, Comment comment);

    Comment deleteComment(long commentId);

}
