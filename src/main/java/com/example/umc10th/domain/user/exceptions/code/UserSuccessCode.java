package com.example.umc10th.domain.user.exceptions.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "COMMON200_1",
            "회원가입이 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
