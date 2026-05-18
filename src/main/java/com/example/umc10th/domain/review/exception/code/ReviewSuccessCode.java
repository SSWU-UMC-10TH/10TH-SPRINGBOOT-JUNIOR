package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATE_REVIEW_SUCCESS(HttpStatus.OK, "REVIEW200_1", "리뷰 작성에 성공했습니다."),
    GET_STORE_REVIEW_SUCCESS(HttpStatus.OK, "REVIEW200_2", "가게 리뷰 조회에 성공했습니다."),
    GET_MY_REVIEWS_BY_ID_SUCCESS(HttpStatus.OK, "REVIEW200_3", "내가 작성한 리뷰 ID순 조회에 성공했습니다."),
    GET_MY_REVIEWS_BY_RATING_SUCCESS(HttpStatus.OK, "REVIEW200_4", "내가 작성한 리뷰 별점순 조회에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}