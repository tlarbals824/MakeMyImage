package com.backend.makemyimage.global.exception.errorDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ErrorResponse {
    private int status;
    private String message;

    public static ErrorResponse of(ErrorCode errorCode){
        return ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .message(errorCode.getMessage())
                .build();
    }
}
