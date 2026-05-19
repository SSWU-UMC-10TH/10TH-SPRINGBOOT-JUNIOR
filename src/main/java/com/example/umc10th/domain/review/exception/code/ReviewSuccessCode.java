package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATE_REVIEW_SUCCESS(
            HttpStatus.OK,
            "REVIEW201_1",
            "리뷰 작성에 성공했습니다."
    ),

    GET_STORE_REVIEW_SUCCESS(
            HttpStatus.OK,
            "REVIEW200_1",
            "식당 리뷰 목록 조회에 성공했습니다."
    ),

    GET_MY_REVIEWS_BY_ID_SUCCESS(
            HttpStatus.OK,
            "REVIEW200_2",
            "내 리뷰 최신순 조회에 성공했습니다."
    ),

    GET_MY_REVIEWS_BY_RATING_SUCCESS(
            HttpStatus.OK,
            "REVIEW200_3",
            "내 리뷰 평점순 조회에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}