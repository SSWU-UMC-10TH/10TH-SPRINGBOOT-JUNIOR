package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @Valid @RequestBody ReviewReqDTO.CreateReview request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATE_REVIEW_SUCCESS,
                reviewService.createReview(request)
        );
    }

    // 식당 리뷰 목록 조회(cursor)
    @GetMapping("/store")
    public ApiResponse<ReviewResDTO.GetStoreReviews> getStoreReviews(
            @RequestParam Long storeId,
            @RequestParam(required = false) Long cursorId,
            @RequestParam Integer size
    ) {
        ReviewReqDTO.GetStoreReviews request =
                new ReviewReqDTO.GetStoreReviews(storeId, cursorId, size);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.GET_STORE_REVIEW_SUCCESS,
                reviewService.getStoreReviews(request)
        );
    }

    // 내가 쓴 리뷰 조회 - 최신순(cursor)
    @GetMapping("/my/id")
    public ApiResponse<ReviewResDTO.GetMyReviewsById> getMyReviewsById(
            @RequestParam Long userId,
            @RequestParam(required = false) Long cursorId,
            @RequestParam Integer size
    ) {
        ReviewReqDTO.GetMyReviewsById request =
                new ReviewReqDTO.GetMyReviewsById(userId, cursorId, size);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.GET_MY_REVIEWS_BY_ID_SUCCESS,
                reviewService.getMyReviewsById(request)
        );
    }

    // 내가 쓴 리뷰 조회 - 평점순(cursor)
    @GetMapping("/my/rating")
    public ApiResponse<ReviewResDTO.GetMyReviewsByRating> getMyReviewsByRating(
            @RequestParam Long userId,
            @RequestParam(required = false) Double cursorRating,
            @RequestParam(required = false) Long cursorId,
            @RequestParam Integer size
    ) {
        ReviewReqDTO.GetMyReviewsByRating request =
                new ReviewReqDTO.GetMyReviewsByRating(userId, cursorRating, cursorId, size);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.GET_MY_REVIEWS_BY_RATING_SUCCESS,
                reviewService.getMyReviewsByRating(request)
        );
    }
}