package com.backend.makemyimage.repository;

import com.backend.makemyimage.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByUserId(Long userId); //userid아니냐
    // 컬럼명이 아닌 변수명을 기준으로 메서드이름 생성!!!

    Optional<Image> findByUserIdAndId(Long userId, Long imageId);
    //이미지 ID도 유저id도 유니크한데 왜 이딴 메서드를 사용했지> 반성하자. 테이블에 대한 이해 부족
    // imageid가 유저마다 1번 인덱스부터 시작할줄...
}




