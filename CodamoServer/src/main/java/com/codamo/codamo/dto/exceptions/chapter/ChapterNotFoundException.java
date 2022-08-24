package com.codamo.codamo.dto.exceptions.chapter;

import com.codamo.codamo.dto.exceptions.handler.BaseException;

public class ChapterNotFoundException extends BaseException {

    public ChapterNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public ChapterNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
