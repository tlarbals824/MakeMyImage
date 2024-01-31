package com.backend.makemyimage.DTO.request.image;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class CreateImageRequestDTO {
    String keyword;
    Long userId;

}
