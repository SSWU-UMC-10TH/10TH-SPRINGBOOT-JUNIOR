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
}
