package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰가 성공적으로 등록되었습니다."),

    // w7 : 내가 작성한 리뷰 목록 조회 성공
    MY_REVIEWS_OK(HttpStatus.OK,
            "REVIEW200_1",
            "내가 작성한 리뷰 목록을 성공적으로 조회했습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
