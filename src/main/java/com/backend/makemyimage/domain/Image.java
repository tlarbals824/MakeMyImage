package com.backend.makemyimage.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="imagee")
public class Image {
    @Id @GeneratedValue
    private Long id;
    private String keyword;
    private String imageUrl;
    private LocalDateTime createTime;

    @Builder
    public Image(Long id, String keyword, String imageUrl, LocalDateTime createTime) {
        this.id = id;
        this.keyword = keyword;
        this.imageUrl = imageUrl;
        this.createTime = createTime;
    }

    public Image() {

    }
}
