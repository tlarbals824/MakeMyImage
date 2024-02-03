package com.backend.makemyimage.repository.image;

import com.backend.makemyimage.domain.image.Image;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepositoryCustom {

    private final EntityManager em;
    @Override
    public void deleteImage(Long id) {
        em.createQuery("update Image i set i.deleted = true where i.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    //회원의 모든 이미지 조회
    @Override
    public List<Image> findAllByLoginId(String loginId) {
        return em.createQuery("select i from Image i where i.loginId = :loginId and i.deleted = false", Image.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }

    //이미지 단건 조회
    @Override
    public Optional<Image> findByImageId(Long id) {
        return Optional.of(em.createQuery("select i from Image i where i.id = :id", Image.class)
                .setParameter("id", id)
                .getSingleResult());
    }
}
