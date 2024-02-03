package com.backend.makemyimage.dto.request.image;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageRequest {

    private String keyword;
    private String loginId;

    @Builder
    public ImageRequest(String keyword, String loginId) {
        this.keyword = keyword;
        this.loginId = loginId;
    }
}
