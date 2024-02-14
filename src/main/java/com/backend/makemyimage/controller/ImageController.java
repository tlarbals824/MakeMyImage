package com.backend.makemyimage.controller;

import com.backend.makemyimage.DTO.request.image.CreateImageRequestDTO;
import com.backend.makemyimage.DTO.response.image.SearchAllImagesResponseDTO;
import com.backend.makemyimage.DTO.response.image.SearchOneImageResponseDTO;
import com.backend.makemyimage.domain.Image;
import com.backend.makemyimage.domain.User;
import com.backend.makemyimage.service.ImageService;
import com.backend.makemyimage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.backend.makemyimage.controller.UserController.LOGIN_MEMBER;

@RestController // 그냥 컨트롤러와 차이
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final UserService userService;

    /**
     * 전체 이미지 조회  by User iD
     * @return
     */
    @GetMapping("/images")
    public List<SearchAllImagesResponseDTO> searchAllImages(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null){
            String loggedInUserEmail = (String) session.getAttribute(LOGIN_MEMBER); //세션에서 로그인 이메일 가져옴
            Long userId = userService.searchByEmail(loggedInUserEmail).getId(); // 그걸로 유저아이디 가져옴
            List<SearchAllImagesResponseDTO> images = imageService.searchAllImages(userId); // 유저아이디 이용해서 조회

            return images;

        }
        else{
            throw new IllegalArgumentException("세션이 없어서 전체 이미지 조회 불가");
            // 클라를 위해 에러반환이 아닌, ApiResponse로 반환? 404반환? 401ㅇㅇ
        }


    }

    /**
     * 단건 이미지 조회  by User iD, image iD
     * @return
     */
    @GetMapping("/image")
    public SearchOneImageResponseDTO searchOneImage(@RequestParam(name="id") Long imageId,HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null){
            String loggedInUserEmail = (String) session.getAttribute(LOGIN_MEMBER); //세션에서 로그인 이메일 가져옴
            Long userId = userService.searchByEmail(loggedInUserEmail).getId(); // 그걸로 유저아이디 가져옴
            SearchOneImageResponseDTO image = imageService.searchOneImage(userId, imageId) ;
            System.out.println("전체 이미지 조회 전! loggedInUserEmail = " + loggedInUserEmail);

            return image;

        }
        else{
            throw new IllegalArgumentException("세션이 없어서 단건 이미지 조회 불가");
        }

    }

    /**
     * 이미지 생성 by User iD
     * @return
     */
    @PostMapping("/image/create") //api에 동사는 쓰지 않는다. 포스트 자체가 생성임

    public String  createImage(@RequestBody  String keyword,  HttpServletRequest request) { //keyword하나인경우도 DTO?
        HttpSession session = request.getSession(false);
        if (session != null){
            String loggedInUserEmail = (String) session.getAttribute(LOGIN_MEMBER); //세션에서 로그인 이메일 가져옴
            Long userId = userService.searchByEmail(loggedInUserEmail).getId(); // 그걸로 유저아이디 가져옴

            CreateImageRequestDTO createImageRequestDTO = CreateImageRequestDTO.builder()
                    .keyword(keyword)
                    .userId(userId)
                    .build();
            return imageService.createImage(createImageRequestDTO);
        }
        else{
            throw new IllegalArgumentException("세션이 없어서 이미지 생성 불가능");
        }

    }

    /**
     * 이미지 제거 by User iD
     * @return
     */
    @DeleteMapping("/image/delete/{imageId}") //얘도 delete라는 동사 x
    public String deleteImage(@PathVariable(name = "imageId") Long imageId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null){
            String loggedInUserEmail = (String) session.getAttribute(LOGIN_MEMBER); //세션에서 로그인 이메일 가져옴
            Long userId = userService.searchByEmail(loggedInUserEmail).getId(); // 그걸로 유저아이디 가져옴


            imageService.deleteImage(userId,imageId); //논리삭제는 미안해요
            return "잘지움";
        }
        else{

            throw new IllegalArgumentException("세션이 없어서 이미지 삭제 불가");
        }



    }
}
