package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게를 찾을 수 없습니다."
    ),

    STORE_ALREADY_DELETED(
            HttpStatus.BAD_REQUEST,
            "STORE400_1",
            "이미 삭제된 가게입니다."
    ),

    STORE_FORBIDDEN(
            HttpStatus.FORBIDDEN,
            "STORE403_1",
            "해당 가게에 대한 권한이 없습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}