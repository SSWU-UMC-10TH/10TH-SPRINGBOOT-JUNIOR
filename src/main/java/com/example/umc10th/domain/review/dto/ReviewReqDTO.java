package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    public record CreateReview(
            Long userId,
            Long storeId,
            String content,
            Float rating
    ) {
    }

    public record GetStoreReviews(
            Long storeId,
            Integer page
    ) {
    }
}