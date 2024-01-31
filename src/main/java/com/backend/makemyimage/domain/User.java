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
@Table(name="userr")
public class User {

    @Id @GeneratedValue
    private Long id;
    private String name;

    private String email;
    private String password;

    @OneToMany
    private List<Image> images = new ArrayList<>();

    @Builder
    public User(Long id, String name, String email, String password, List<Image> images) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.images = images;
    }

    public User() { //왜?
    }
}
