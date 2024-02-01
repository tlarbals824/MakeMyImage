package com.backend.makemyimage.DTO.response.image;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class KarloResponseDTO {
    private String id;
    private String model_version;
    private List<ImageDTO> images;

    // Getters and Setters

    @Getter @Setter
    public static class ImageDTO {
        private String id;
        private String image;
        private long seed;
        private Boolean nsfw_content_detected;
        private Double nsfw_score;

        // Getters and Setters
    }
}
