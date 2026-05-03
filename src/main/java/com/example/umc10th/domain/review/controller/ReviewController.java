package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(
            value = "/my-page/completed-missions/{userMissionId}/reviews",

            // 리뷰 이미지를 multipartfile로 받겠다는 뜻
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    ) public ApiResponse<ReviewResDTO.CreateReview> createReview (
            @PathVariable Long userMissionId,
            @RequestPart("review") ReviewReqDTO.CreateReview dto,
            @RequestPart(value="images", required=false) List<MultipartFile> images
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, reviewService.createReview(userMissionId, dto, images));
    }

    // w7 : 내가 작성한 리뷰 조회 - ID 순
    @GetMapping("/users/reviews")
    public ApiResponse<ReviewResDTO.MyReviewListResponse> getMyReviewsById(
            @RequestParam Long userId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.MY_REVIEWS_OK,
                reviewService.getMyReviewsById(userId, cursor, size)
        );
    }

    // w7 : 내가 작성한 리뷰 조회 - 별점 순
    @GetMapping("/users/reviews/rating")
    public ApiResponse<ReviewResDTO.MyReviewRatingListResponse> getMyReviewsByRating(
            @RequestParam Long userId,
            @RequestParam(required = false) Double ratingCursor,
            @RequestParam(required = false) Long reviewIdCursor,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.MY_REVIEWS_OK,
                reviewService.getMyReviewsByRating(
                        userId,
                        ratingCursor,
                        reviewIdCursor,
                        size
                )
        );
    }
}
