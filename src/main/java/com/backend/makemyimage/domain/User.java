package com.backend.makemyimage.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id @GeneratedValue
    private Long id;
    private String name;

    private String email;
    private String password;

    @OneToMany
    private List<Image> images = new ArrayList<>();


}
