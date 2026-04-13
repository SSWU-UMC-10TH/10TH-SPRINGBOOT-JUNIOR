package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    // 회원가입
    public record SignUp(
            @NotBlank String name,
            @NotBlank String nickname,
            @Email String email,
            @NotBlank String password,
            @NotBlank LocalDate birth,
            @NotBlank String addressLine1,
            @NotBlank String addressLine2,
            @NotBlank List<Integer> preferenceFoodIds
    ) {}
}
