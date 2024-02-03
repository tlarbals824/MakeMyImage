package com.backend.makemyimage.repository.image;

import com.backend.makemyimage.domain.image.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepositoryCustom {
    void deleteImage(Long id);

    List<Image> findAllByLoginId(String id);

    Optional<Image> findByImageId(Long id);
}
