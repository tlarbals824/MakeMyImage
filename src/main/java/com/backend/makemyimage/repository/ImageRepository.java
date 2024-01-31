package com.backend.makemyimage.repository;

import com.backend.makemyimage.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
//    Optional<Image> findById(Long id); //id가 유저아이디?이미지아이디? 컬럼명바까

    List<Image> findAllByUserId(Long userId); //userid아니냐

    Optional<Image> findByUserIdAndId(Long userId, Long imageId);}

