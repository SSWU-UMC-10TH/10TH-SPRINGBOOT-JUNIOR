package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class UserResDTO {

    // 회원가입
    @Builder
    public record SignUp(
            String name,
            String nickname,
            String email
    ) {}
}
