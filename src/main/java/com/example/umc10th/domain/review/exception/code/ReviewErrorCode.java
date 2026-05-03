package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    MISSION_COMPLETED_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "완료된 미션을 찾을 수 없습니다."
    ),

    INVALID_RATING(
            HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "별점은 0점 이상 5점 이하로 입력해야 합니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}