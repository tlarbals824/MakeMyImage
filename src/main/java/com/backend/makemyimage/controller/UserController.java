package com.backend.makemyimage.controller;

import com.backend.makemyimage.DTO.request.user.JoinRequestDTO;
import com.backend.makemyimage.DTO.request.user.LoginRequestDTO;
import com.backend.makemyimage.DTO.request.user.SearchUserInfoRequestDTO;
import com.backend.makemyimage.DTO.response.user.SearchUserInfoResponseDTO;
import com.backend.makemyimage.domain.User;
import com.backend.makemyimage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    public static final String LOGIN_MEMBER = "loginMember";
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
    public String postLogin(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest request) {


        boolean loginResult = userService.login(loginRequestDTO);
        if (loginResult) {
            //세션 발행

            HttpSession session = request.getSession(); // 세션 발행
            session.setAttribute(LOGIN_MEMBER, loginRequestDTO.getEmail()); //세션저장소에저장
            
        } else {
            //not

        }
        return "redirect:/"; //로그인 하면 일단 / 라우트로 이동
        //이건 타임리프 기준이고, ApiResponse라는 베이스객체를 만들어서 사용하자
    }

    /**
     * 로그인 후 이동할 홈매퍼?? 왜 필요한거지  ...
     */
    @GetMapping("/")
    public String homeLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("새션없잔아 미친");
            return "no sess";
        } else {
            Object poppedUserId = session.getAttribute(LOGIN_MEMBER);
            System.out.println("세숀잇어요 userID : " +poppedUserId );
            return "Sess!!";
        }
    }


}
