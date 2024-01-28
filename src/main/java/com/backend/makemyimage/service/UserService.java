package com.backend.makemyimage.service;

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

    public void join(User user) {
        userRepository.save(user);
    }

    public User findById(Long id) throws IllegalStateException{
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 유저 id입니다.");
        }
        return optionalUser.get();
    }
}
