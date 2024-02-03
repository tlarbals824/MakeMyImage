package com.backend.makemyimage.controller.member;

import com.backend.makemyimage.base.BaseResponse;
import com.backend.makemyimage.dto.request.member.LoginRequest;
import com.backend.makemyimage.dto.request.member.MemberRequest;
import com.backend.makemyimage.dto.response.member.LoginResponse;
import com.backend.makemyimage.dto.response.member.MemberInfoResponse;
import com.backend.makemyimage.service.member.MemberService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 API", description = "회원 관련 API 입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "https://make-my-image-app.vercel.app")
public class MemberController {
    private final MemberService memberService;

    //로그인
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public BaseResponse<LoginResponse> memberLogin(@RequestBody LoginRequest req, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()) {
            //에러 발생
            ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
            return BaseResponse.onFailure(400, objectError.getDefaultMessage(), null);
        }

        LoginResponse response = memberService.userLogin(req);
        return BaseResponse.onSuccess(response);
    }

    //회원가입
    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public void create(@RequestBody MemberRequest req) {
        memberService.create(req);
    }

    //회원 정보 조회
    @Operation(summary = "회원정보 조회")
    @GetMapping("/info/{loginId}")
    public BaseResponse<MemberInfoResponse> memberInfo(@PathVariable @Parameter(name = "사용자 ID", example = "kusitms") String loginId) {
        try {
            MemberInfoResponse memberInfoResponse = memberService.getMemberInfo(loginId);
            return BaseResponse.onSuccess(memberInfoResponse);
        }
        catch(Exception e) {
            return BaseResponse.onFailure(400, e.getMessage(), null);
        }
    }

    @Hidden
    @GetMapping("/healty")
    public BaseResponse<Integer> healtyEc2() {
        return BaseResponse.onHeealty();
    }
}
