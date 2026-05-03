package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    public record SignUp(

            @NotBlank(message = "이름은 필수 입력값입니다.")
            @Size(max = 20, message = "이름은 20자 이하로 입력해주세요.")
            String name,

            @NotBlank(message = "닉네임은 필수 입력값입니다.")
            @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하로 입력해주세요.")
            @Pattern(
                    regexp = "^[가-힣a-zA-Z0-9_]+$",
                    message = "닉네임은 한글, 영문, 숫자, 언더바(_)만 사용할 수 있습니다."
            )
                String nickname,

            @NotBlank(message = "이메일은 필수 입력값입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수 입력값입니다.")
            @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
            String password,

            @NotNull(message = "생년월일은 필수 입력값입니다.")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate birth,

            @NotBlank(message = "기본 주소는 필수 입력값입니다.")
            String addressLine1,

            @NotBlank(message = "상세 주소는 필수 입력값입니다.")
            String addressLine2,

            @NotEmpty(message = "선호 음식 카테고리는 최소 1개 이상 선택해야 합니다.")
            List<Integer> preferenceFoodIds
        ) {}
    }
