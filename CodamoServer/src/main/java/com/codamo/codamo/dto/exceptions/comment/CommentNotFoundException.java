package com.codamo.codamo.dto.exceptions.comment;

import com.codamo.codamo.dto.exceptions.handler.BaseException;

public class CommentNotFoundException extends BaseException {

    public CommentNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public CommentNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
