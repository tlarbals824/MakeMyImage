package com.backend.makemyimage.controller.image;

import com.backend.makemyimage.base.BaseResponse;
import com.backend.makemyimage.dto.request.image.ImageRequest;
import com.backend.makemyimage.dto.response.image.ImageListResponse;
import com.backend.makemyimage.dto.response.image.ImageResponse;
import com.backend.makemyimage.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
@CrossOrigin(origins = "https://make-my-image-app.vercel.app")
public class ImageController {

    private final ImageService imageService;

    //이미지 생성
    @PostMapping("")
    public BaseResponse<ImageResponse> create(@RequestBody ImageRequest req) {
        try {
            ImageResponse imageResponse = imageService.create(req);
            return BaseResponse.onSuccess(imageResponse);
        }
        catch (Exception e) {
            return BaseResponse.onFailure(400, "이미지 생성에 실패하였습니다.", null);
        }
    }

    //모든 이미지 조회
    @GetMapping("/{loginId}/images")
    public ImageListResponse getAll(@PathVariable String loginId) {
        return imageService.getAllByLoginId(loginId);
    }

    //특정 이미지 조회
    @GetMapping("/{imageId}")
    public ImageResponse get(@PathVariable Long imageId) {
        return imageService.getImageById(imageId);
    }

    //이미지 삭제
    @DeleteMapping("/{imageId}")
    public void delete(@PathVariable Long imageId) {
        imageService.delete(imageId);
    }

}
