package com.backend.makemyimage.service;

import com.backend.makemyimage.domain.User;
import com.backend.makemyimage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void join(User user) {
        userRepository.join(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }
}
