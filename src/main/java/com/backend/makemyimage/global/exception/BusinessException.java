package com.backend.makemyimage.global.exception;

import com.backend.makemyimage.global.exception.errorDto.ErrorCode;
import lombok.Getter;

//그냥 일단 한번에 처리
@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}