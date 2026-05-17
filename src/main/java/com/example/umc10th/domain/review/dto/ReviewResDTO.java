package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    // 리뷰 작성
    @Builder
    public record CreateReview(
            // 생성된 리뷰 아이디
            Long review_id,

            // 사용자가 수행한 미션들 중 어떤 미션 id인지
            Long user_mission_id,

            String store_name,
            Double rating,
            String content,

            // 리뷰 이미지들을 s3에 등록 후 얻은 url
            List<String> imageUrls,

            // 작성자 닉네임
            String nickname,
            String createdAt
    ) {}

    // w7 : 내가 작성한 리뷰 조회 응답 아이템
    public record MyReviewResponse(
            Long reviewId,
            String storeName,
            String content,
            Double rating,
            String nickname,
            String createdAt
    ) {}

    // w7 : 내가 작성한 리뷰 ID 순 조회 응답
    public record MyReviewListResponse(
            List<MyReviewResponse> reviews,
            Long cursor,
            Boolean hasNext
    ) {}

    // w7 : 내가 작성한 리뷰 별점 순 조회 응답
    public record MyReviewRatingListResponse(
            List<MyReviewResponse> reviews,
            Double ratingCursor,
            Long reviewIdCursor,
            Boolean hasNext
    ) {}
}
