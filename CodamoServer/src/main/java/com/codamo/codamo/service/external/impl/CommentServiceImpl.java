package com.codamo.codamo.service.external.impl;

import com.codamo.codamo.dto.comment.request.CreateCommentRequest;
import com.codamo.codamo.dto.comment.request.UpdateCommentRequest;
import com.codamo.codamo.dto.comment.response.CommentResponse;
import com.codamo.codamo.dto.exceptions.comment.CommentNotFoundException;
import com.codamo.codamo.repo.CommentRepo;
import com.codamo.codamo.service.external.CommentService;
import com.codamo.codamo.service.internal.CommentInternalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    private final CommentInternalService commentInternalService;

    public CommentServiceImpl(CommentRepo commentRepo, CommentInternalService commentInternalService) {
        this.commentRepo = commentRepo;
        this.commentInternalService = commentInternalService;
    }

    @Override
    public CommentResponse createComment(CreateCommentRequest createCommentRequest) {
        return null;
    }

    @Override
    public CommentResponse getComment(String id) throws CommentNotFoundException {
        return null;
    }

    @Override
    public List<CommentResponse> getAllComments() {
        return null;
    }

    @Override
    public CommentResponse updateComment(UpdateCommentRequest updateCommentRequest) throws CommentNotFoundException {
        return null;
    }

    @Override
    public void deleteComment(String id) throws CommentNotFoundException {

    }
}
