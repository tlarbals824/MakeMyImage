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
            System.out.println("컨트롤러로그인성공");
            HttpSession session = request.getSession(); // 세션 발행
            session.setAttribute(LOGIN_MEMBER, loginRequestDTO.getEmail()); //세션저장소에저장
            
        } else {
            //not
            System.out.println("컨트롤러로그인실패");

        }
        return "redirect:/"; //로그인 하면 일단 / 라우트로 이동
    }

    /**
     * 로그인 후 이동할 홈매퍼??
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

    /**
     * 세션확인용
     */
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다.";
        }
        // 세션 id와 저장된 객체 정보 출력
        System.out.println(session.getId() + ", " + session.getAttribute("loginMember"));

//        //세션 데이터 출력
//        session.getAttributeNames().asIterator()
//                .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));
//
//        log.info("sessionId={}", session.getId());
//        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
//        log.info("creationTime={}", new Date(session.getCreationTime()));
//        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
//        log.info("isNew={}", session.isNew());

        return "세션 출력";

    }
}
