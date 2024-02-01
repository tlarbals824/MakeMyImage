package com.backend.makemyimage.controller;

import com.backend.makemyimage.DTO.request.image.CreateImageRequestDTO;
import com.backend.makemyimage.DTO.response.image.SearchAllImagesResponseDTO;
import com.backend.makemyimage.DTO.response.image.SearchOneImageResponseDTO;
import com.backend.makemyimage.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 그냥 컨트롤러와 차이
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    /**
     * 전체 이미지 조회  by User iD
     * @return
     */
    @GetMapping("/images")
    public List<SearchAllImagesResponseDTO> searchAllImages() {
        List<SearchAllImagesResponseDTO> images = imageService.searchAllImages(2L);

        return images;
    }

    /**
     * 단건 이미지 조회  by User iD, image iD
     * @return
     */
    @GetMapping("/image")
    public SearchOneImageResponseDTO searchOneImage(@RequestParam(name="id") Long imageId) {
        SearchOneImageResponseDTO image = imageService.searchOneImage(2L, imageId) ;

        return image;
    }

    /**
     * 이미지 생성 by User iD
     * @return
     */
    @PostMapping("/image/create")
    public String  createImage() {
        CreateImageRequestDTO createImageRequestDTO = CreateImageRequestDTO.builder()
                .keyword("bts")
                .userId(2L)
                .build();

        return imageService.createImage(createImageRequestDTO);
    }

    @GetMapping("/test")
    public String testFeign() {
        return "feign!";
    }
}
