package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReview(

            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,

            @NotBlank(message = "리뷰 제목을 입력해야 합니다.")
            @Size(max = 50, message = "리뷰 제목은 50자 이하로 입력해주세요.")
            String review_title,

            @NotBlank(message = "리뷰 내용을 입력해야 합니다.")
            @Size(max = 1000, message = "리뷰 내용은 1000자 이하로 입력해주세요.")
            String review_comment,

            @NotNull(message = "평점은 필수입니다.")
            @DecimalMin(value = "1.0", message = "리뷰 평점은 1점 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "리뷰 평점은 5점 이하이어야 합니다.")
            Double rating
    ) {
    }

    // 식당 리뷰 목록에서 보여줄 리뷰 1개 조회
    public record GetReview(
            @NotNull(message = "리뷰 ID는 필수입니다.")
            Long reviewId
    ) {
    }

    // 식당 리뷰 목록 리스트 조회(cursor)
    public record GetStoreReviews(

            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,

            Long cursorId,

            // 몇 개씩 가져올지
            @NotNull(message = "조회 개수는 필수입니다.")
            @Min(value = 1, message = "조회 개수는 1 이상이어야 합니다.")
            Integer size
    ) {
    }

    // 내가 작성한 리뷰 1개
    public record GetMyReview(

            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            @NotNull(message = "리뷰 ID는 필수입니다.")
            Long reviewId
    ) {
    }

    // 내가 쓴 리뷰 조회 - 최신순(cursor)
    public record GetMyReviewsById(

            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            // 최신순 조회
            Long cursorId,

            // 몇 개씩 가져올지
            @NotNull(message = "조회 개수는 필수입니다.")
            @Min(value = 1, message = "조회 개수는 1 이상이어야 합니다.")
            Integer size
    ) {
    }

    // 내가 쓴 리뷰 조회 - 평점순(cursor)
    public record GetMyReviewsByRating(

            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            // 평점순 조회
            Double cursorRating,

            // 평점이 같으면 최신순
            Long cursorId,

            // 몇 개씩 가져올지
            @NotNull(message = "조회 개수는 필수입니다.")
            @Min(value = 1, message = "조회 개수는 1 이상이어야 합니다.")
            Integer size
    ) {
    }
}