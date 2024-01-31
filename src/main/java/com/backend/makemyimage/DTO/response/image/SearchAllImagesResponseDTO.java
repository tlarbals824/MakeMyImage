package com.backend.makemyimage.DTO.response.image;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter //이거 추가하니까 된건가 DTO에 게터세터가 왜 있어야햐ㅐㅂㅉㄸ?!#!@#
public class SearchAllImagesResponseDTO {
    String imageUrl;
    LocalDateTime createTime;
}
