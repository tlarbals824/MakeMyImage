package com.backend.makemyimage.DTO.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class JoinResponseDTO {
    String name;
    String email;
    String password;


}
