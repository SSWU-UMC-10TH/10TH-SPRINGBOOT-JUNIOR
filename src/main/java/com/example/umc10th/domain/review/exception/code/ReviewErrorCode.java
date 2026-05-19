package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰를 찾을 수 없습니다."
    ),

    REVIEW_ALREADY_DELETED(
            HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "이미 삭제된 리뷰입니다."
    ),

    REVIEW_FORBIDDEN(
            HttpStatus.FORBIDDEN,
            "REVIEW403_1",
            "해당 리뷰에 대한 권한이 없습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}