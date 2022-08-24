package com.codamo.codamo.service.external;

import com.codamo.codamo.dto.comment.request.CreateCommentRequest;
import com.codamo.codamo.dto.comment.request.UpdateCommentRequest;
import com.codamo.codamo.dto.comment.response.CommentResponse;
import com.codamo.codamo.dto.exceptions.comment.CommentNotFoundException;

import java.util.List;

public interface CommentService {

    CommentResponse createComment(CreateCommentRequest createCommentRequest);

    CommentResponse getComment(String id) throws CommentNotFoundException;

    List<CommentResponse> getAllComments();

    CommentResponse updateComment(UpdateCommentRequest updateCommentRequest) throws CommentNotFoundException;

    void deleteComment(String id) throws CommentNotFoundException;
}
