package com.example.umc10th.domain.user.exceptions.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    OK_SIGNUP(HttpStatus.OK,
            "COMMON200_1",
            "회원가입이 완료되었습니다."),
    OK_LOGIN(HttpStatus.OK,
            "COMMON200_2",
            "로그인이 완료되었습니다.")  ,
    OK_MYPAGE(HttpStatus.OK,
            "COMMON200_3",
            "마이 페이지 조회 완료!")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
