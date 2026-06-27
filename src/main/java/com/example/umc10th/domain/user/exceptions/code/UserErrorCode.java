package com.example.umc10th.domain.user.exceptions.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_01",
            "해당 사용자를 찾을 수 없습니다."),

    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST,
            "USER400_01",
            "이미 존재하는 이메일입니다."),

    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST,
            "USER400_02",
            "이미 존재하는 닉네임입니다."),

    INVAlID_PASSWORD(HttpStatus.UNAUTHORIZED,
            "USER401_01",
            "비밀번호가 일치하지 않습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
