package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReview(
            Long reviewId,
            Long userId,
            Long storeId,
            String content,
            Float rating,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ReviewPreview(
            Long reviewId,
            String nickname,
            String content,
            Float rating,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ReviewPreviewList(
            List<ReviewPreview> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }
}