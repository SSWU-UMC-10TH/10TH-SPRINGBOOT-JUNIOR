package com.example.umc10th.domain.user.dto;

import java.time.LocalDateTime;

public class UserResDTO {

    public record GetMyPage(
            String nickname,
            String profileUrl,
            String email,
            String phoneNumber,
            Long point
    ) {
    }

    public record SignUp(
            Long userId,
            String email,
            String nickname,
            LocalDateTime createdAt
    ) {
    }
}