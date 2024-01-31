package com.backend.makemyimage.service;

import com.backend.makemyimage.DTO.request.image.CreateImageRequestDTO;
import com.backend.makemyimage.DTO.response.image.SearchAllImagesResponseDTO;
import com.backend.makemyimage.DTO.response.image.SearchOneImageResponseDTO;
import com.backend.makemyimage.domain.Image;
import com.backend.makemyimage.domain.User;
import com.backend.makemyimage.repository.ImageRepository;
import com.backend.makemyimage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;  //이거 써도 돼?? 이미지서비스에서?

    public List<SearchAllImagesResponseDTO> searchAllImages(Long userId) {
        List<Image> images = imageRepository.findAllByUserId(userId);
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

    public SearchOneImageResponseDTO searchOneImage(Long userId, Long imageId) {
        Optional<Image> optionalImage = imageRepository.findByUserIdAndId(userId, imageId);
        Image image = optionalImage.get();

        SearchOneImageResponseDTO transferredImage = SearchOneImageResponseDTO.builder()
                .imageUrl(image.getImageUrl())
                .keyword(image.getKeyword())
                .createTime(image.getCreateTime())
                .build();

        return transferredImage;
    }


    public String createImage(CreateImageRequestDTO createImageRequestDTO) {
         //post 방식의 api 호출하고 결과물을 db에 저장
        String imageUrl = callKarloAPI(createImageRequestDTO.getKeyword());

        Optional<User> optionalUser = userRepository.findById(createImageRequestDTO.getUserId());
        User user = optionalUser.get(); //없을때 예외처리?? 너무 코드 더ㅓㄹ워

        Image image = Image.builder()
                .keyword(createImageRequestDTO.getKeyword())
                .imageUrl(imageUrl)
                .createTime(LocalDateTime.now())
                .user(user)
                .build();

        imageRepository.save(image);
        return imageUrl;
    }

    private String callKarloAPI(String keyword) {
        return "https://mk.kakaocdn.net/dna/karlo/image/2024-01-31/21/f107327a-1fa2-4ca9-8862-7acf3a4afa1a.webp?credential=smxRqiqUEJBVgohptvfXS5JoYeFv4Xxa&expires=1706706309&signature=UtZtuhx8PJUb7f9Y64EJe3N6pxw%3D";
    }
}
