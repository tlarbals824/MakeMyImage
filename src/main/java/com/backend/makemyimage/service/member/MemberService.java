package com.backend.makemyimage.service.member;

import com.backend.makemyimage.domain.member.Member;
import com.backend.makemyimage.dto.request.member.LoginRequest;
import com.backend.makemyimage.dto.request.member.MemberRequest;
import com.backend.makemyimage.dto.response.member.LoginResponse;
import com.backend.makemyimage.dto.response.member.MemberInfoResponse;
import com.backend.makemyimage.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public void create(MemberRequest memberRequest) {
        //회원가입
        Member newMember= Member.builder()
                .loginId(memberRequest.getLoginId())
                .password(memberRequest.getPassword())
                .email(memberRequest.getEmail())
                .build();

        memberRepository.save(newMember);
    }

    @Transactional
    public LoginResponse userLogin(LoginRequest req) {
        //유저 로그인
        Member findMember = memberRepository.findByLoginId(req.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디 혹은 비밀번호 입니다."));

        if(!findMember.getPassword().equals(req.getPassword())) {
            // 비밀번호가 일치하지 않는다면
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

//        String secretKey = "make-my-image";
//        long expireTime = 1000 * 60 * 60; // 토큰 만료시간 = 60분
        
        // Jwt accessToken 생성
        String accessToken = "access-token";
        return LoginResponse.builder()
                .loginId(findMember.getLoginId())
                .email(findMember.getEmail())
                .accessToken(accessToken)
                .build();
    }

    public MemberInfoResponse getMemberInfo(String loginId) {
        // 멤버 정보 조회
        if(loginId == null) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
        if(optionalMember.isEmpty()) {
            throw new IllegalArgumentException("정보를 찾을 수 없습니다.");
        }

        Member findMember = optionalMember.get();

        return MemberInfoResponse.builder()
                .loginId(findMember.getLoginId())
                .email(findMember.getEmail())
                .build();
    }
}
