package com.backend.makemyimage.service;

import com.backend.makemyimage.DTO.request.user.JoinRequestDTO;
import com.backend.makemyimage.DTO.request.user.LoginRequestDTO;
import com.backend.makemyimage.DTO.request.user.SearchUserInfoRequestDTO;
import com.backend.makemyimage.DTO.response.user.SearchUserInfoResponseDTO;
import com.backend.makemyimage.domain.User;
import com.backend.makemyimage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입 비즈니스 로직
     */
    public void join(JoinRequestDTO joinRequestDTO) {

        User user = User.builder()
                .name(joinRequestDTO.getName())
                .email(joinRequestDTO.getEmail())
                .password(joinRequestDTO.getPassword())
                .build(); // 매퍼 써라

        userRepository.save(user);
    }

    //이거 뿌듯한데 맞게 했는가? 서비스로직에서 이렇게 하는건가 ㅇㅇ맞음
    //DTO에 유저같은 엔티티는 들어가면 안되겟지 당연히?ㅋㅋ 그럼 userId로 매번 레포지토리에서 찾아와야하나
    public SearchUserInfoResponseDTO searchUserInfo(SearchUserInfoRequestDTO searchUserInfoRequestDTO) throws IllegalStateException{
        Optional<User> optionalUser = userRepository.findById(searchUserInfoRequestDTO.getId());
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 유저 id입니다.");
        }
        User user = optionalUser.get();

        SearchUserInfoResponseDTO searchUserInfoResponseDTO = SearchUserInfoResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build(); //이거도 매퍼로. 매퍼가 그리좋다네

        return searchUserInfoResponseDTO;
    }

    /**
     * 로그인 로직
     * @return
     */
    public boolean login(LoginRequestDTO loginRequestDTO) { //유저를 반환하도록 해야하나? 컨트롤러에 유저라는 엔티티가 노출돼도 돼?
        // 이런 서비스로직 메서드에서 무엇을 반환타입을 할 지 감이안옴
        try {
            Optional<User> optionalUser = userRepository.findByEmail(loginRequestDTO.getEmail());
            if (optionalUser.isEmpty()) {
                throw new IllegalStateException("존재하지 않는 email 입니다.");
            }
            User user = optionalUser.get();
            if (!user.getPassword().equals(loginRequestDTO.getPassword())) {
                throw new IllegalStateException("비밀번호가 맞지 않습니다.");
            }
            System.out.println("로그인 성공!");
            return true;
        } catch (IllegalStateException e) {
            // 에러 발생 시 로그 및 false 반환
            System.out.println("로그인 실패: " + e.getMessage());
            return false;
        }
    }

    public User searchByEmail(String email) { //유저를 반환하는 쓰레기같은 로직인가? 유저 id만 반환할까?
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.get();
    }
}
