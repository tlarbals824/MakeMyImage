package com.backend.makemyimage.DTO.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter //필요 없나?
@Setter
//@Builder
public class LoginRequestDTO {
    String email;
    String password;

    @Builder
    public LoginRequestDTO(String email, String password) {
        this.email =email;
        this.password = password;
    }

}
