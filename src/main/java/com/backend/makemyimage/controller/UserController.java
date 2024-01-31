package com.backend.makemyimage.controller;

import com.backend.makemyimage.DTO.request.user.JoinRequestDTO;
import com.backend.makemyimage.DTO.request.user.LoginRequestDTO;
import com.backend.makemyimage.DTO.request.user.SearchUserInfoRequestDTO;
import com.backend.makemyimage.DTO.response.user.SearchUserInfoResponseDTO;
import com.backend.makemyimage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 회원가입 api
     */
    @PostMapping("/user/join")
    public String postUserJoin(@RequestBody JoinRequestDTO joinRequestDTO) {
        userService.join(joinRequestDTO);
        return "redirect:/";
    }

    /**
     * 사용자 정보 조회 api
     */
    @GetMapping("/user/{userId}")
    public SearchUserInfoResponseDTO getUserInfo(@PathVariable Long userId) {
        SearchUserInfoRequestDTO searchUserInfoRequestDTO = SearchUserInfoRequestDTO.builder()
                .id(userId)
                .build();

        return userService.searchUserInfo(searchUserInfoRequestDTO);
    }

    /**
     * 로그인 api
     */
    @PostMapping("/login")
    public String postLogin(@RequestBody LoginRequestDTO loginRequestDTO){
        return userService.login(loginRequestDTO);
    }
}
