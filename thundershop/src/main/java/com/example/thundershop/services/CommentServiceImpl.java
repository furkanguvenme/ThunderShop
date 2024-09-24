package com.example.thundershop.services;

import com.example.thundershop.entity.Comment;
import com.example.thundershop.entity.Product;
import com.example.thundershop.entity.ProductReviews;
import com.example.thundershop.entity.User;
import com.example.thundershop.exceptions.ThunderException;
import com.example.thundershop.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ProductService productService, UserService userService) {
        this.commentRepository = commentRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public Comment saveComment(long userId, long productId, Comment comment) {
        Product foundProduct = productService.findProductById(productId);
        User foundUser = userService.findUserById(userId);

        ProductReviews userComments = new ProductReviews();
        userComments.setComment(comment);
        userComments.setUser(foundUser);
        userComments.setProduct(foundProduct);

        foundProduct.getProductReviewsList().add(userComments);
        foundUser.getProductReviewsList().add(userComments);
        comment.getProductReviewsList().add(userComments);

        return commentRepository.save(comment);
    }

    @Override
    public Comment deleteComment(long commentId) {
        if(commentRepository.findById(commentId).isPresent()){
            Comment foundComment = commentRepository.findById(commentId).get();
            commentRepository.delete(foundComment);
            return foundComment;
        }
        throw new ThunderException("There is not comment which have " + commentId, HttpStatus.BAD_REQUEST);
    }
}
