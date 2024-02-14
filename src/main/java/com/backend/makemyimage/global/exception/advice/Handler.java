package com.backend.makemyimage.global.exception.advice;

import com.backend.makemyimage.global.exception.errorDto.ErrorCode;
import com.backend.makemyimage.global.exception.errorDto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class Handler {

    //그냥 return new 로 만드는게 좋은건가????
    @ExceptionHandler
    public ErrorResponse handler(MethodArgumentNotValidException e){
        log.error("[MethodArgumentNotValidException] ex", e);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.BAD_REQUEST);
        return errorResponse;
    }
}
