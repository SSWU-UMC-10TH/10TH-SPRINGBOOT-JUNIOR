package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;

public class ReviewReqDTO {

    public record CreateReview(

            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,

            @NotBlank(message = "리뷰 내용을 입력해야 합니다.")
            @Size(max = 1000, message = "리뷰 내용은 1000자 이하로 입력해주세요.")
            String content,

            @NotNull(message = "평점은 필수입니다.")
            @DecimalMin(value = "1.0", message = "리뷰 평점은 1점 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "리뷰 평점은 5점 이하이어야 합니다.")
            Double rating
    ) {
    }

    public record GetStoreReviews(

            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,

            @NotNull(message = "페이지 번호는 필수입니다.")
            @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
            Integer page
    ) {
    }

    public record GetMyReviewsById(

            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            Long cursorId,

            @NotNull(message = "조회 개수는 필수입니다.")
            @Min(value = 1, message = "조회 개수는 1 이상이어야 합니다.")
            Integer size
    ) {
    }

    public record GetMyReviewsByRating(

            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            Double cursorRating,

            Long cursorId,

            @NotNull(message = "조회 개수는 필수입니다.")
            @Min(value = 1, message = "조회 개수는 1 이상이어야 합니다.")
            Integer size
    ) {
    }
}