package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserResDTO {

    // 회원가입
    public record SignUp(
            String name,
            String nickname,
            String email
    ) {}
}
