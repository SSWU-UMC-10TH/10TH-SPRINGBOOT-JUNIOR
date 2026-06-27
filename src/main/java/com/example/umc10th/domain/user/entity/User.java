package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.dto.UserReqDTO;
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

    @Column(name = "password", nullable = false)
    private String password;

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
    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    // 상세 주소
    @Column(name = "address_line2", nullable = false)
    private String addressLine2;

    // 누적 포인트
    @Column(name = "point", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long point;

    @Column(name = "social_uid")
    private String socialUID;

    @Column(name = "social_type")
    private SocialType socialType;

    // 사용자 닉네임 (회원가입할 때 입력 필수!)
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "phone_number")
    private String phoneNumber;

    // UserService 말고 엔티티에 역할 및 책임 전가, 엔티티 외부에선 빌더 사용하지 않도록!
    public static User toUser(UserReqDTO.SignUp dto, String encodedPassword) {
        return User.builder()
                .name(dto.name())
                .nickname(dto.nickname())
                .email(dto.email())
                .password(encodedPassword)
                .birth(dto.birth())
                .gender(dto.gender())
                .addressLine1(dto.addressLine1())
                .addressLine2(dto.addressLine2())
                .build();
    }
}