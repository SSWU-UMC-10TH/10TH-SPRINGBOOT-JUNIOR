package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다."),
    REVIEW_CONTENT_EMPTY(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 내용을 입력해야 합니다."),
    REVIEW_RATING_INVALID(HttpStatus.BAD_REQUEST, "REVIEW400_2", "리뷰 평점은 1점 이상 5점 이하이어야 합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}