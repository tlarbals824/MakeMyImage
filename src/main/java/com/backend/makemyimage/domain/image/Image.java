package com.backend.makemyimage.domain.image;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Schema(description = "이미지 생성 키워드", nullable = false, example = "cat, doctor, happy")
    private String keyword; //사용자가 제시한 키워드

    @NotNull
    @Schema(description = "이미지를 생성한 사용자의 ID", nullable = false)
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
