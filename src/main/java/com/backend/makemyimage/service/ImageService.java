package com.backend.makemyimage.service;

import com.backend.makemyimage.DTO.request.image.CreateImageRequestDTO;
import com.backend.makemyimage.DTO.request.user.JoinRequestDTO;
import com.backend.makemyimage.DTO.response.image.KarloResponseDTO;
import com.backend.makemyimage.DTO.response.image.SearchAllImagesResponseDTO;
import com.backend.makemyimage.DTO.response.image.SearchOneImageResponseDTO;
import com.backend.makemyimage.DTO.response.user.JoinResponseDTO;
import com.backend.makemyimage.api.KarloOpenFeign;
import com.backend.makemyimage.domain.Image;
import com.backend.makemyimage.domain.User;
import com.backend.makemyimage.repository.ImageRepository;
import com.backend.makemyimage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;  //이거 써도 돼?? 이미지서비스에서?
    private final KarloOpenFeign karloOpenFeign;


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
        KarloResponseDTO karloResponse = callKarloAPI(createImageRequestDTO.getKeyword());

        Optional<User> optionalUser = userRepository.findById(createImageRequestDTO.getUserId());
        User user = optionalUser.get(); //없을때 예외처리?? 너무 코드 더ㅓㄹ워

        Image image = Image.builder()
                .keyword(createImageRequestDTO.getKeyword())
                .imageUrl(karloResponse.getImages().get(0).getImage())
                .createTime(LocalDateTime.now())
                .user(user)
                .build();

        imageRepository.save(image);
        return karloResponse.getImages().get(0).getImage();
    }

    private KarloResponseDTO callKarloAPI(String keyword) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("prompt", keyword);

        KarloResponseDTO karloResponse = karloOpenFeign.createImageKarlo(requestBody, "application/json", "KakaoAK f842b493ff26f47c87f481c478cc0c80");
        return karloResponse;
    }

    public void deleteImage(Long userId, Long imageId) { //이런거 파라미터가 DTO가 되어야 함???????
        Optional<Image> optionalImage = imageRepository.findByUserIdAndId(userId, imageId);
        imageRepository.delete(optionalImage.get()); // 이럴거면 옵셔널 왜써

    }
}
