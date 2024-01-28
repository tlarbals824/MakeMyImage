package com.backend.makemyimage;

import com.backend.makemyimage.domain.User;
import com.backend.makemyimage.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MakeMyImageApplicationTests {

    UserRepository userRepository;
    @Test
    public void joinAndFindByIdTest() {
        User user = new User();
        user.setName("심규민");
        user.setEmail("simkm@gmail.com");


    }

}
