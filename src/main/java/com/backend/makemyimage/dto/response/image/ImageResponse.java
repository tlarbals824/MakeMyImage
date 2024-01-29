package com.backend.makemyimage.dto.response.image;

import com.backend.makemyimage.domain.image.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ImageResponse {
    private Long imageId;
    private String url;
    private String keyword;
    private LocalDateTime createdAt;

    @Builder
    public ImageResponse(Image image) {
        this.imageId = image.getId();
        this.url = image.getUrl();
        this.keyword = image.getKeyword();
        this.createdAt = image.getCreatedAt();
    }
}
