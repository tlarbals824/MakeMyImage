package com.backend.makemyimage;

import com.backend.makemyimage.domain.image.Image;
import com.backend.makemyimage.domain.member.Member;
import com.backend.makemyimage.dto.request.image.ImageRequest;
import com.backend.makemyimage.dto.request.member.MemberRequest;
import com.backend.makemyimage.dto.response.image.ImageListResponse;
import com.backend.makemyimage.dto.response.image.ImageResponse;
import com.backend.makemyimage.repository.image.ImageRepository;
import com.backend.makemyimage.service.image.ImageService;
import com.backend.makemyimage.service.member.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ImageTest {
    @Autowired
    private ImageService imageService;
    @Autowired
    private  MemberService memberService;
    @Autowired
    private ImageRepository imageRepository;

    @Test
    @DisplayName("이미지 객체 생성 테스트")
    void createImage() throws Exception {
        //given
        Member member = getMember();
        ImageRequest imageRequest = new ImageRequest("keyword", "id");
        //when
        imageService.create(imageRequest);
        //then
        List<Image> images = imageRepository.findAllByLoginId("id");
        assertThat(images).hasSize(1);
        Image image = images.get(0);
        assertThat(image.getUrl()).isEqualTo("123");
        assertThat(image.getLoginId()).isEqualTo(member.getLoginId());
    }

    @Test
    @DisplayName("Karlo 이미지 생성 테스트")
    void generateImage() throws Exception {
        //given
        Member member = getMember();
        ImageRequest imageRequest = new ImageRequest("cat, macbook, sweet", "id");
        imageService.create(imageRequest);
        //when
        ImageListResponse myImages = imageService.getAllByLoginId("id");
        List<ImageResponse> imageList = myImages.getImageList();
        //then
        assertThat(imageList).hasSize(1);
    }

    @Test
    @DisplayName("이미지 단건 조회 테스트")
    void findImageById() {
        //given
        ImageResponse findImage = imageService.getImageById(1L);
        //when
        //then
        assertThat(findImage.getImageId()).isEqualTo(1L);
    }

    private Member getMember() {
        Member member = new Member("id", "password", "jaeguk@kusitms.com");
        MemberRequest memberRequest = new MemberRequest(member.getLoginId(), member.getPassword(), member.getEmail());
        memberService.create(memberRequest);
        return member;
    }
}
