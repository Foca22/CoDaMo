package com.codamo.codamo.service.internal.impl;

import com.codamo.codamo.dto.comment.response.CommentResponse;
import com.codamo.codamo.dto.exceptions.comment.CommentNotFoundException;
import com.codamo.codamo.dto.exceptions.messages.ExceptionMessages;
import com.codamo.codamo.model.comment.Comment;
import com.codamo.codamo.repo.CommentRepo;
import com.codamo.codamo.service.internal.CommentInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentInternalServiceImpl implements CommentInternalService {

    private final CommentRepo commentRepo;

    @Autowired
    public CommentInternalServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public Comment findCommentById(String id) throws CommentNotFoundException {
        Optional<Comment> commentOptional = commentRepo.findById(id);
        if(commentOptional.isEmpty()){
            throw new CommentNotFoundException(ExceptionMessages.COMMENT_NOT_FOUND.getErrorMessage(),
                    ExceptionMessages.COMMENT_NOT_FOUND.getHttpStatusCode());
        }
        return commentOptional.get();
    }

    @Override
    public CommentResponse toCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                comment.getAccount().getId(),
                comment.getLesson().getId());
    }
}
