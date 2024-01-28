package com.backend.makemyimage.service;

import com.backend.makemyimage.domain.User;
import com.backend.makemyimage.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

//    @Autowired
//    UserService userService;
//
//    @Autowired
//    UserRepository userRepository;
//    @Test
//    public void joinAndFindByIdTest() {
//        User user = new User();
//        user.setName("심규민민규");
//        user.setPassword("1q2w3e4r");
//
//
//    }
}
