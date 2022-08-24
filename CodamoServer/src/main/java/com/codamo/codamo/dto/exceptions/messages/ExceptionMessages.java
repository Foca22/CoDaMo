package com.codamo.codamo.dto.exceptions.messages;

import org.springframework.http.HttpStatus;

public enum ExceptionMessages {

    COURSE_NOT_FOUND("Course not found!", HttpStatus.NOT_FOUND.toString()),
    CHAPTER_NOT_FOUND("Chapter not found!", HttpStatus.NOT_FOUND.toString()),
    LESSON_NOT_FOUND("Lesson not found!", HttpStatus.NOT_FOUND.toString()),
    COMMENT_NOT_FOUND("Comment not found!", HttpStatus.NOT_FOUND.toString());
    
    private final String errorMessage;
    private final String httpStatusCode;

    ExceptionMessages(String errorMessage, String httpStatusCode) {
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }
}
