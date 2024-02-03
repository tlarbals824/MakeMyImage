package com.backend.makemyimage;

import com.backend.makemyimage.domain.member.Member;
import com.backend.makemyimage.dto.request.member.LoginRequest;
import com.backend.makemyimage.dto.request.member.MemberRequest;
import com.backend.makemyimage.dto.response.member.LoginResponse;
import com.backend.makemyimage.dto.response.member.MemberInfoResponse;
import com.backend.makemyimage.repository.member.MemberRepository;
import com.backend.makemyimage.service.member.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 테스트")
    void createMember() throws Exception {
        //given
        Member member = new Member("id", "password", "jaeguk@kusitms.com");
        MemberRequest memberRequest = new MemberRequest(member.getLoginId(), member.getPassword(), member.getEmail());
        //when
        memberService.create(memberRequest);
        //then
        MemberInfoResponse memberInfo = memberService.getMemberInfo("id");
        Optional<Member> optionalMember = memberRepository.findByLoginId(member.getLoginId());

        //회원이 존재해야 하고
        assertThat(optionalMember).isPresent();

        Member findMember = optionalMember.get();

        //정보가 일치해야 한다
        assertThat(findMember.getLoginId()).isEqualTo(member.getLoginId());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
    }

    @Test
    @DisplayName("회원정보 조회 테스트")
    void getMemberInfo() throws Exception {
        //given
        Member member = getMember();
        //when
        MemberInfoResponse memberInfo = memberService.getMemberInfo(member.getLoginId());
        //then
        assertThat(memberInfo.getLoginId()).isEqualTo(member.getLoginId());
        assertThat(memberInfo.getEmail()).isEqualTo(member.getEmail());
    }

    @Test
    @DisplayName("로그인 테스트")
    void login() throws Exception {
        //given
        Member member = getMember();
        //when
        LoginRequest req = new LoginRequest("id", "password");
        LoginResponse loginResponse = memberService.userLogin(req);
        //then
        assertThat(loginResponse.getAccessToken()).isEqualTo("access-token");
    }

    private Member getMember() {
        Member member = new Member("id", "password", "jaeguk@kusitms.com");
        MemberRequest memberRequest = new MemberRequest(member.getLoginId(), member.getPassword(), member.getEmail());
        memberService.create(memberRequest);
        return member;
    }
}
