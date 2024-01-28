package com.backend.makemyimage.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="imagee")

public class Image {
    @Id @GeneratedValue
    private Long id;
    private String image;
}
