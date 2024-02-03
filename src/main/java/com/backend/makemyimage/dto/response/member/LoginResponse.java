package com.backend.makemyimage.dto.response.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponse {
    private String loginId;
    private String email;
    private String accessToken;

    @Builder
    public LoginResponse(String loginId, String email, String accessToken) {
        this.loginId = loginId;
        this.accessToken = accessToken;
        this.email = email;
    }
}
