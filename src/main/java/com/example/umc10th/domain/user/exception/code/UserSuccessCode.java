package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    SIGN_UP_SUCCESS(
            HttpStatus.OK,
            "USER200_1",
            "회원가입에 성공했습니다."
    ),

    GET_MY_PAGE_SUCCESS(
            HttpStatus.OK,
            "USER200_2",
            "마이페이지 조회에 성공했습니다."
    ),

    LOGIN_SUCCESS(
            HttpStatus.OK,
            "USER200_3",
            "로그인에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}