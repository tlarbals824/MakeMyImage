package com.backend.makemyimage.repository.image;

import com.backend.makemyimage.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom {
    Optional<Image> findById(Long id);
    List<Image> findAllByLoginId(String loginId);
}

