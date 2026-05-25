package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.enums.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserReqDTO {

    // 마이페이지 요청 시 NullPointerException 방지
    public record GetMyPage(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long userId
    ) {
    }

    // 회원가입
    public record SignUp(

            @NotBlank(message = "이름은 필수입니다.")
            String name,

            @NotNull(message = "성별은 필수입니다.")
            Gender gender,

            @NotNull(message = "생년월일은 필수입니다.")
            LocalDate birthDate,

            @NotBlank(message = "닉네임은 필수입니다.")
            @Size(min = 2, max = 30, message = "닉네임은 2자 이상 30자 이하로 입력해주세요.")
            String nickname,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 4, max = 100, message = "비밀번호는 4자 이상 100자 이하로 입력해주세요.")
            String password,

            String phoneNumber,

            String profileUrl
    ) {
    }

    // 로그인
    public record Login(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password
    ) {
    }
}