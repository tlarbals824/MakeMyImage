package com.backend.makemyimage.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class SearchUserInfoResponseDTO {
    String name;
    String email;
}
