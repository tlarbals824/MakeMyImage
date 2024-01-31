package com.backend.makemyimage.controller;

import com.backend.makemyimage.DTO.request.LoginRequestDTO;
import com.backend.makemyimage.domain.User;
import com.backend.makemyimage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 회원가입 api
     */
    @PostMapping("/user/join")
    public String postUserJoin(User user) {
        User user1 = new User();
        user1.setName("심규민민규민니규");
        user1.setEmail("simkm@gmail.com");
        user1.setPassword("1q2w3e4r");

        userService.join(user1);
        return "redirect:/";
    }

    /**
     * 사용자 정보 조회 api
     */
    @GetMapping("/user/{userId}")
    public User getUserInfo(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    /**
     * 로그인 api
     */
    @PostMapping("/login")
    public String postLogin(@RequestBody LoginRequestDTO loginRequestDTO){
        return userService.login(loginRequestDTO);
    }
}
