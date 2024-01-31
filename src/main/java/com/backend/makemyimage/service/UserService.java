package com.backend.makemyimage.service;

import com.backend.makemyimage.DTO.request.JoinRequestDTO;
import com.backend.makemyimage.DTO.request.LoginRequestDTO;
import com.backend.makemyimage.DTO.request.SearchUserInfoRequestDTO;
import com.backend.makemyimage.DTO.response.SearchUserInfoResponseDTO;
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

    public void join(JoinRequestDTO joinRequestDTO) {

        User user = User.builder()
                .name(joinRequestDTO.getName())
                .email(joinRequestDTO.getEmail())
                .password(joinRequestDTO.getPassword())
                .build();

        userRepository.save(user);
    }

    //이거 뿌듯한데 맞게 했는가? 서비스로직에서 이렇게 하는건가
    public SearchUserInfoResponseDTO searchUserInfo(SearchUserInfoRequestDTO searchUserInfoRequestDTO) throws IllegalStateException{
        Optional<User> optionalUser = userRepository.findById(searchUserInfoRequestDTO.getId());
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 유저 id입니다.");
        }
        User user = optionalUser.get();

        SearchUserInfoResponseDTO searchUserInfoResponseDTO = SearchUserInfoResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        return searchUserInfoResponseDTO;
    }

    public String login(LoginRequestDTO loginRequestDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 email 입니다.");
        }
        User user = optionalUser.get();
        if(!user.getPassword().equals(loginRequestDTO.getPassword())){
            throw new IllegalStateException("비밀번호가 맞지 않습니다.");
        }
        System.out.println("로그인 성공!");
        return "login 성공";
    }
}
