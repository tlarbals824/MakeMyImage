package com.backend.makemyimage.domain.image;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Image {
    @Id @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    private String url;

    private String keyword; //사용자가 제시한 키워드

    @JoinColumn(name = "login_id")
    private String loginId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean deleted; //삭제 여부(soft delete)

    @Builder
    public Image(String keyword, String url, String loginId) {
        this.keyword = keyword;
        this.url = url;
        this.loginId = loginId;
        this.deleted = false;
    }
}
