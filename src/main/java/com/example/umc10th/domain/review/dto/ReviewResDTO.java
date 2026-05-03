package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    public record CreateReview(
            Long reviewId,
            Long userId,
            Long storeId,
            String content,
            Double rating,
            LocalDateTime createdAt
    ) {
    }

    public record ReviewPreview(
            Long reviewId,
            String nickname,
            String content,
            Double rating,
            LocalDateTime createdAt
    ) {
    }

    public record ReviewPreviewList(
            List<ReviewPreview> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    public record MyReview(
            Long reviewId,
            String storeName,
            String content,
            Double rating,
            LocalDateTime createdAt
    ) {
    }

    public record CursorPagination(
            List<MyReview> reviewList,
            Integer listSize,
            Boolean hasNext,
            Long nextCursorId,
            Double nextCursorRating
    ) {
    }
}