package com.backend.makemyimage.dto.response.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberInfoResponse {
    // Member 정보 반환
    private String loginId;
    private String email;

    @Builder
    public MemberInfoResponse(String loginId, String email) {
        this.loginId = loginId;
        this.email = email;
    }
}
