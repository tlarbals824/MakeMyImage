package com.backend.makemyimage.repository.image;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepositoryCustom {

    private final EntityManager em;
    @Override
    public void deleteImage(Long id) {
        em.createQuery("update Image i set i.deleted = false where i.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
