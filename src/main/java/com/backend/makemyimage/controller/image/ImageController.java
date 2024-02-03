package com.backend.makemyimage.controller.image;

import com.backend.makemyimage.base.BaseResponse;
import com.backend.makemyimage.dto.request.image.ImageRequest;
import com.backend.makemyimage.dto.response.image.ImageListResponse;
import com.backend.makemyimage.dto.response.image.ImageResponse;
import com.backend.makemyimage.service.image.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "이미지 API", description = "이미지 관련 API 입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
@CrossOrigin(origins = "https://make-my-image-app.vercel.app")
public class ImageController {

    private final ImageService imageService;

    //이미지 생성
    @Operation(summary = "이미지 생성", description = "새로운 이미지를 생성합니다.")
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
    @Operation(summary = "모든 이미지 조회", description = "회원이 생성한 모든 이미지를 조회합니다.")
    @GetMapping("/{loginId}/images")
    public ImageListResponse getAll(@PathVariable @Parameter(name = "loginId", description = "사용자 ID") String loginId) {
        return imageService.getAllByLoginId(loginId);
    }

    //특정 이미지 조회
    @Operation(summary = "특정 이미지 조회", description = "회원이 생성한 특정 이미지를 조회합니다.")
    @GetMapping("/{imageId}")
    public ImageResponse get(@PathVariable @Parameter(name = "imageId", description = "이미지의 ID", example = "1") Long imageId) {
        return imageService.getImageById(imageId);
    }

    //이미지 삭제
    @Operation(summary = "이미지 삭제", description = "이미지를 삭제합니다.")
    @DeleteMapping("/{imageId}")
    public void delete(@PathVariable @Parameter(name = "imageId", description = "이미지의 ID", example = "1") Long imageId) {
        imageService.delete(imageId);
    }

}
