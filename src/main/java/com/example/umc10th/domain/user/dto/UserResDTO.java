package com.example.umc10th.domain.user.dto;

import lombok.Builder;

public class UserResDTO {

    // 회원가입
    @Builder
    public record SignUp(
            String name,
            String nickname,
            String email
    ) {}

    @Builder
    public record Login(
            String accessToken
    ) {}

    // 마이페이지 조회
    @Builder
    public record MyPage(
            String nickname,
            String email,
            String phoneNumber,
            Boolean isPhoneVerified,
            Long point
    ) {}
}
