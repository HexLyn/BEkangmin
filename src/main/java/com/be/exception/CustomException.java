package com.be.exception;

import com.be.common.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode exceptionCode;
    private final Throwable throwable;

    public CustomException(ErrorCode exceptionCode) {
        this.exceptionCode = exceptionCode;
        this.throwable = null;
    }

    public CustomException(Exception exception, ErrorCode exceptionCode) {
        this.exceptionCode = exceptionCode;
        this.throwable = exception.getCause();
    }

    public CustomException(RuntimeException exception, ErrorCode exceptionCode) {
        this.exceptionCode = exceptionCode;
        this.throwable = exception.getCause();
    }

    public CustomException(IOException exception, ErrorCode exceptionCode) {
        this.exceptionCode = exceptionCode;
        this.throwable = exception.getCause();
    }

    public CustomException(IllegalArgumentException exception, ErrorCode exceptionCode) {
        this.exceptionCode = exceptionCode;
        this.throwable = exception.getCause();
    }
}
