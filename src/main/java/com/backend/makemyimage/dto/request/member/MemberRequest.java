package com.backend.makemyimage.dto.request.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequest {
    //회원가입 요청 dto
    private String loginId;
    private String password;
    private String email;

    @Builder
    public MemberRequest(String loginId, String password,
                         String email) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
    }
}
