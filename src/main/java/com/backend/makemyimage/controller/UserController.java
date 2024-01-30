package com.backend.makemyimage.controller;

import com.backend.makemyimage.domain.User;
import com.backend.makemyimage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 회원가입 api
     */
    @GetMapping("/user/join")
    public String postUserJoin(User user) {
        User user1 = new User();
        user1.setName("심규민민규민니규");
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
}
