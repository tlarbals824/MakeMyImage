package com.backend.makemyimage.dto.response.image;

import com.backend.makemyimage.domain.image.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageResponse {
    private Long id;
    private String url;
    private String keyword;


    @Builder
    public ImageResponse(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
        this.keyword = image.getKeyword();
    }
}
