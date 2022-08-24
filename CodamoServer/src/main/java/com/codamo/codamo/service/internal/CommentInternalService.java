package com.codamo.codamo.service.internal;

import com.codamo.codamo.dto.comment.response.CommentResponse;
import com.codamo.codamo.dto.exceptions.comment.CommentNotFoundException;
import com.codamo.codamo.model.comment.Comment;

public interface CommentInternalService {

    Comment findCommentById(String id) throws CommentNotFoundException;

    CommentResponse toCommentResponse(Comment comment);
}
