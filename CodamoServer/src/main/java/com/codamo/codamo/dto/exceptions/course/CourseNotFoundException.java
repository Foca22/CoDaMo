package com.codamo.codamo.dto.exceptions.course;

import com.codamo.codamo.dto.exceptions.handler.BaseException;

public class CourseNotFoundException extends BaseException {

    public CourseNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public CourseNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
