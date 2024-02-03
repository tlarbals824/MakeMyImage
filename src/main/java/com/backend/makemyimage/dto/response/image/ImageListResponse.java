package com.backend.makemyimage.dto.response.image;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ImageListResponse {
    private List<ImageResponse> imageList;

    @Builder
    public ImageListResponse(List<ImageResponse> imageList) {
        this.imageList = imageList;
    }
}
