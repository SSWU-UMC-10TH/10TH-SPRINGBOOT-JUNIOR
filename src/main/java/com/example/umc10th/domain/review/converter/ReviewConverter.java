package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    // w7 : 내가 작성한 리뷰 응답 변환
    public static ReviewResDTO.MyReviewResponse toMyReviewResponse(Review review) {

        return new ReviewResDTO.MyReviewResponse(
                review.getId(),
                review.getStore().getName(),
                review.getContent(),
                review.getRating(),
                review.getUser().getNickname(),
                review.getCreatedAt().toString()
        );
    }
}
