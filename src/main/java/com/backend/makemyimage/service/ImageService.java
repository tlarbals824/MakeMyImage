package com.backend.makemyimage.service;

import com.backend.makemyimage.DTO.response.SearchAllImagesResponseDTO;
import com.backend.makemyimage.domain.Image;
import com.backend.makemyimage.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;

    public List<SearchAllImagesResponseDTO> searchAllImages() {
        List<Image> images = imageRepository.findAll();
        System.out.println("images = " + images.size());
        //객체 변환 챗지피티 감사요
        List<SearchAllImagesResponseDTO> transferredImages = images.stream()
                .map(image -> SearchAllImagesResponseDTO.builder()
                        .imageUrl(image.getImageUrl())
                        .createTime(image.getCreateTime())
                        .build())
                .collect(Collectors.toList());
        System.out.println("transferredImages = " + transferredImages.size());
        return transferredImages;
    }

    public String createImage(String keyword) {
         //post 방식의 api 호출하고 결과물을 db에 저장
        String imageUrl = callKarloAPI(keyword);

        Image image = Image.builder()
                .keyword(keyword)
                .imageUrl(imageUrl)
                .createTime(LocalDateTime.now())
                .build();

        imageRepository.save(image);
        return imageUrl;
    }

    private String callKarloAPI(String keyword) {
        return "https://mk.kakaocdn.net/dna/karlo/image/2024-01-31/21/f107327a-1fa2-4ca9-8862-7acf3a4afa1a.webp?credential=smxRqiqUEJBVgohptvfXS5JoYeFv4Xxa&expires=1706706309&signature=UtZtuhx8PJUb7f9Y64EJe3N6pxw%3D";
    }
}
