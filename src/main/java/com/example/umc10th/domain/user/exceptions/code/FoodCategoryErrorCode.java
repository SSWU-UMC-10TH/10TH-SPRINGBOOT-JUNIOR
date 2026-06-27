package com.example.umc10th.domain.user.exceptions.code; // 프로젝트 카테고리 패키지 위치에 맞게 수정 가능합니다!

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FoodCategoryErrorCode implements BaseErrorCode {

    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD404_01",
            "존재하지 않는 음식 카테고리입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}