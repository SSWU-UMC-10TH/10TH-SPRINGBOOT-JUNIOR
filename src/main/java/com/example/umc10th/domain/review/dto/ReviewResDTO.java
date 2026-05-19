package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // 리뷰 작성 성공 요청
    public record CreateReview(
            Long reviewId,
            Long userId,
            Long storeId,
            String reviewTitle,
            String reviewComment,
            Double rating,
            LocalDateTime createdAt
    ) {
    }

    // 식당 리뷰 목록에서 보여줄 리뷰 1개 조회
    public record GetStoreReview(
            Long reviewId,
            Long userId,
            String nickname,
            Long storeId,
            String storeName,
            String reviewTitle,
            String reviewComment,
            Double rating,
            LocalDateTime createdAt
    ) {
    }

    // 식당 리뷰 목록 리스트 조회(cursor)
    public record GetStoreReviews(
            List<GetStoreReview> reviewList,
            Integer listSize,
            Boolean hasNext,
            Long nextCursorId
    ) {
    }

    // 내가 작성한 리뷰 1개
    public record GetMyReview(
            Long reviewId,
            Long userId,
            String nickname,
            Long storeId,
            String storeName,
            String reviewTitle,
            String reviewComment,
            Double rating,
            LocalDateTime createdAt
    ) {
    }

    // 내가 쓴 리뷰 조회 - 최신순(cursor)
    public record GetMyReviewsById(
            List<GetMyReview> reviewList,
            Integer listSize,
            Boolean hasNext,
            Long nextCursorId
    ) {
    }

    // 내가 쓴 리뷰 조회 - 평점순(cursor)
    public record GetMyReviewsByRating(
            List<MyReview> reviewList,
            Integer listSize,
            Boolean hasNext,
            Long nextCursorId,
            Double nextCursorRating
    ) {
    }
}