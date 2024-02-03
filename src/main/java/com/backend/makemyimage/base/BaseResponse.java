package com.backend.makemyimage.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> { // BaseResponse 객체를 사용할때 성공, 실패 경우
    private final int code;
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    /**
     * 요청에 성공한 경우
     */
    public static <T> BaseResponse<T> onSuccess(T result){
        return new BaseResponse<>(1000, true, "요청에 성공하였습니다.", result);
    }

    /**
     * 무조건 성공 반환 (로드밸런스 상태검사)
     */
    public static <T> BaseResponse<T> onHeealty() {
        return new BaseResponse<>(200, true, "건강합니다.", null);
    }

    /**
     * 요청에 실패한 경우
     */
    public static <T> BaseResponse<T> onFailure(int code, String message, T result) {
        return new BaseResponse<>(code, false, message, result);
    }
}