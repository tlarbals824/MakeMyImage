package com.backend.makemyimage.domain.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @NotNull
    private Long id;

    @Column(name = "login_id", unique = true)
    @NotNull
    @Schema(description = "사용자 ID", nullable = false, example = "kusitms")
    private String loginId;

    @NotNull
    @Schema(description = "사용자 비밀번호", nullable = false)
    private String password;

    @Email
    @NotNull
    @Schema(description = "사용자 이메일", nullable = false, example = "jaeguk@kusitms.com")
    private String email;

    @Builder
    public Member(String loginId, String password, String email) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
    }
}
