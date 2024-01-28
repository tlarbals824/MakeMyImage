package com.backend.makemyimage.repository;

import com.backend.makemyimage.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void Join(User user) {
        em.persist(user);
    }
}
