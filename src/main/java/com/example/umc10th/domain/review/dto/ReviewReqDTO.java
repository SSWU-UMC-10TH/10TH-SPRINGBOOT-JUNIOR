package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    // 리뷰 작성
    // 파일은 DTO가 아니라 MultipartFile로 따로 받음
    public record CreateReview(
            String content,
            Double rating
    ) {}
}
