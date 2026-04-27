package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.CreateReview toCreateReview(Review review) {
        return ReviewResDTO.CreateReview.builder()
                .reviewId(review.getReviewId())
                .userId(review.getUser().getUserId())
                .storeId(review.getStore().getStoreId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewPreview toReviewPreview(Review review) {
        return ReviewResDTO.ReviewPreview.builder()
                .reviewId(review.getReviewId())
                .nickname(review.getUser().getNickname())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewPreviewList toReviewPreviewList(Page<Review> reviewPage) {
        List<ReviewResDTO.ReviewPreview> reviewList = reviewPage.stream()
                .map(ReviewConverter::toReviewPreview)
                .toList();

        return ReviewResDTO.ReviewPreviewList.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}