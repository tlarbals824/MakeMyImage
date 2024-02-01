package com.backend.makemyimage.repository;

import com.backend.makemyimage.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByUserId(Long userId); //userid아니냐

    Optional<Image> findByUserIdAndId(Long userId, Long imageId);}


