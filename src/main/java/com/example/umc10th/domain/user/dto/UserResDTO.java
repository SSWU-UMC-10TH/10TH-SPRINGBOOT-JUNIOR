package com.example.umc10th.domain.user.dto;

import java.time.LocalDateTime;

public class UserResDTO {

    // 마이페이지
    public record GetMyPage(
            String nickname,
            String profileUrl,
            String email,
            String phoneNumber,
            Long point
    ) {
    }

    // 회원가입
    public record SignUp(
            Long userId,
            String email,
            String nickname,
            LocalDateTime createdAt
    ) {
    }

    // 로그인
    public record Login(
            String accessToken
    ) {
    }
}