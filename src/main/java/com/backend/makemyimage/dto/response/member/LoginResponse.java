package com.backend.makemyimage.dto.response.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponse {

    private String accessToken;
    @Builder
    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
