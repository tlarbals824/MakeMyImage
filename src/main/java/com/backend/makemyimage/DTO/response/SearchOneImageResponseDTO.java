package com.backend.makemyimage.DTO.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class SearchOneImageResponseDTO {
    String imageUrl;
    String keyword;
    LocalDateTime createTime;
}
