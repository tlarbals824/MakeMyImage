package com.backend.makemyimage.domain;

import jakarta.persistence.*;
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
    @Column(name = "image_id")
    private Long id; //얘로 한다
    private String keyword;
    private String imageUrl;
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Image(Long id, String keyword, String imageUrl, LocalDateTime createTime,User user) {
        this.id = id;
        this.keyword = keyword;
        this.imageUrl = imageUrl;
        this.createTime = createTime;
        this.user = user; // 이거맞나
    }

    public Image() {

    }
}
