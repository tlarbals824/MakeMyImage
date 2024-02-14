package com.backend.makemyimage.domain.image.domain;

import com.backend.makemyimage.domain.user.domain.User;
import com.backend.makemyimage.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "image")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //무분별한 객체 생성 체크
@AllArgsConstructor
@Builder
public class Image extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    private String keyword;
    private String url;
    private boolean isDelete;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
