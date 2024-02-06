package com.backend.makemyimage.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="userr") //user 는 왜 !!!!! sql 예약어??
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String name;

    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Image> images ; // 얘가 없어도 된다 업애기
    //이미지에서 파인드바이유저아이디, 이미지레포지토리

    @Builder
    public User(Long id, String name, String email, String password, List<Image> images) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.images = images;
    }

    public User() { //왜? : 자바 상식. 생성자 오버로딩을 할거면 기본 생성자가 있어야 한다.
    }
}
