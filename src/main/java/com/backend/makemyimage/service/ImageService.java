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

    /**
     * 이미지 전체 조회 비즈니스로직
     * @return
     */
    public List<SearchAllImagesResponseDTO> searchAllImages(Long userId) {
        List<Image> images = imageRepository.findAllByUserId(userId);
        //객체 변환 챗지피티 감사요. DTO -> 엔티티변환 또는 DTO -> DTO 변환은 "매퍼"라는 것을 사용해보란다(친구가)
        List<SearchAllImagesResponseDTO> transferredImages = images.stream()
                .map(image -> SearchAllImagesResponseDTO.builder()
                        .imageUrl(image.getImageUrl())
                        .createTime(image.getCreateTime())
                        .build())
                .collect(Collectors.toList());
        return transferredImages;
    }

    /**
     * 이미지 단건 조회 빗즈니스 로직
     * @return
     */
    public SearchOneImageResponseDTO searchOneImage(Long userId, Long imageId) { //이거도 두개부터 DTO?
        Optional<Image> optionalImage = imageRepository.findByUserIdAndId(userId, imageId);
        Image image = optionalImage.get();

        SearchOneImageResponseDTO transferredImage = SearchOneImageResponseDTO.builder()
                .imageUrl(image.getImageUrl())
                .keyword(image.getKeyword())
                .createTime(image.getCreateTime())
                .build();

        return transferredImage; //이거도 매퍼를 사용하든지 메서드를 분리하면 좋을 듯
    }

    /**
     * 이미지 생성 비즈니스 로직
     * 여기서 서비스로직의 이미지생성과 컨트롤러로직의 이미지생성 메서드의 이름을 같게 해도 되나? 직관적일텐데
     * @return
     */
    public String createImage(CreateImageRequestDTO createImageRequestDTO) { //서비스에서도 DTO를 해야하나??? ㅇㅇ 해야함. 클라 -> 컨트롤러도 DTO, 컨트롤러 -> 서비스도 DTO
         //post 방식의 api 호출하고 결과물을 db에 저장
        KarloResponseDTO karloResponse = callKarloAPI(createImageRequestDTO.getKeyword());

        Optional<User> optionalUser = userRepository.findById(createImageRequestDTO.getUserId());
        User user = optionalUser.get(); //없을때 예외처리?? 너무 코드 더러운데 -> throw or not인가 그거 깔끔하더라

        Image image = Image.builder()
                .keyword(createImageRequestDTO.getKeyword())
                .imageUrl(karloResponse.getImages().get(0).getImage())
                .createTime(LocalDateTime.now())
                .user(user)
                .build(); //=매퍼!

        imageRepository.save(image);
        return karloResponse.getImages().get(0).getImage();
    }

    //얘 위치가 어디로 빠져야 이상적일까...
    private KarloResponseDTO callKarloAPI(String keyword) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("prompt", keyword);

        //키 숨기는 방식은 면접 이후에 좀 찾아보겟음,.. 보안조까
        KarloResponseDTO karloResponse = karloOpenFeign.createImageKarlo(requestBody, "application/json", "KakaoAK f842b493ff26f47c87f481c478cc0c80");
        return karloResponse;
    }

    /**
     * 이미지 삭제 비즈니스 로직
     */
    public void deleteImage(Long userId, Long imageId) { //이런거 파라미터가 DTO가 되어야 함??????? ㅇㅇ 되어야함
        Optional<Image> optionalImage = imageRepository.findByUserIdAndId(userId, imageId);
        imageRepository.delete(optionalImage.get()); // 이럴거면 옵셔널 왜써. isEmpty 확인하든가

    }
}
