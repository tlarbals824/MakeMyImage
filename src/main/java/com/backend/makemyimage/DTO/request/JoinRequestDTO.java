package com.backend.makemyimage.DTO.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder //생성자로 안만들고 이렇게만 써도 돼?
public class JoinRequestDTO {
    String name;
    String email;
    String password;


}
