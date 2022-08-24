package com.codamo.codamo.dto.exceptions.lesson;

import com.codamo.codamo.dto.exceptions.handler.BaseException;

public class LessonNotFoundException extends BaseException {

    public LessonNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public LessonNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
