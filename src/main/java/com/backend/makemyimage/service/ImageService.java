package com.backend.makemyimage.service;

import com.backend.makemyimage.DTO.response.SearchAllImagesResponseDTO;
import com.backend.makemyimage.domain.Image;
import com.backend.makemyimage.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public List<SearchAllImagesResponseDTO> searchAllImages() {
        List<Image> images = imageRepository.findAll();

        //객체 변환 챗지피티 감사요
        List<SearchAllImagesResponseDTO> transferredImages = images.stream()
                .map(image -> SearchAllImagesResponseDTO.builder()
                        .imageUrl(image.getImageUrl())
                        .createTime(image.getCreateTime())
                        .build())
                .collect(Collectors.toList());

        return transferredImages;
    }
}
