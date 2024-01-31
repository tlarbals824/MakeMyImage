package com.backend.makemyimage.DTO.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class SearchAllImagesResponseDTO {
    String imageUrl;
    LocalDateTime createTime;
}
