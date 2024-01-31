package com.backend.makemyimage.controller;

import com.backend.makemyimage.DTO.response.SearchAllImagesResponseDTO;
import com.backend.makemyimage.domain.Image;
import com.backend.makemyimage.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 그냥 컨트롤러와 차이
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    /**
     * 전체 이미지 조회
     * @return
     */
    @GetMapping("/images")
    public List<SearchAllImagesResponseDTO> searchAllImages() {
        List<SearchAllImagesResponseDTO> images = imageService.searchAllImages();

        return images;
    }

    @PostMapping("/image/create")
    public String  createImage() {

        return imageService.createImage("puppy");
    }
}
