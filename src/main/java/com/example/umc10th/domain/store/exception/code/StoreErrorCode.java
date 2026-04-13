package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    private final HttpStatus status;
    private final String code;
    private final String message;
}
