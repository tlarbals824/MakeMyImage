package com.backend.makemyimage.DTO.response.image;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter // 이거하니까 not acceptable도 사라짐 40-6
public class SearchOneImageResponseDTO {
    String imageUrl;
    String keyword;
    LocalDateTime createTime;
}
