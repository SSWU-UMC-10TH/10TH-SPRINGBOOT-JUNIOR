package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;

public class ReviewReqDTO {

    // 리뷰 작성
    // 파일은 DTO가 아니라 MultipartFile로 따로 받음
    public record CreateReview(

            @NotBlank(message = "리뷰 내용은 필수 입력값입니다.")
            @Size(min = 5, max = 500, message = "리뷰 내용은 5자 이상 500자 이하로 입력해주세요.")
            String content,

            @NotNull(message = "별점은 필수 입력값입니다.")
            @DecimalMin(value = "0.0", message = "별점은 0.0점 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5.0점 이하이어야 합니다.")
            Double rating
    ) {}
}
