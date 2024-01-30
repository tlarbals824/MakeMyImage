package com.backend.makemyimage.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id", unique = true)
    private String loginId;

    private String password;

    private String email;

    @Builder
    public Member(String loginId, String password, String email) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
    }
}
