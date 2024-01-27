package com.backend.makemyimage.domain.member;

import com.backend.makemyimage.domain.image.Image;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    private String password;

    private String email;

    @OneToMany
    private List<Image> images;

    public void addImages(Image image) {
        this.images.add(image);
    }

    @Builder
    public Member(String loginId, String password, String email) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.images = new ArrayList<>();
    }
}
