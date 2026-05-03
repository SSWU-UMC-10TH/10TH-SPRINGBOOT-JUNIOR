package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    // 사용자 실제 이름
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NOT_SELECTED;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    // 기본 주소
    @Column(name = "addressLine1", nullable = false)
    private String addressLine1;

    // 상세 주소
    @Column(name = "addressLine2", nullable = false)
    private String addressLine2;

    // 누적 포인트
    @Column(name = "point", nullable = false)
    private Long point;

    @Column(name = "social_uid")
    private String socialUID;

    @Column(name = "socialType")
    private SocialType socialType;

    // 사용자 닉네임
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}