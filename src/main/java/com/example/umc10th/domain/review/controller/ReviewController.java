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

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @Valid @RequestBody ReviewReqDTO.CreateReview request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATE_REVIEW_SUCCESS,
                reviewService.createReview(request)
        );
    }

    @PostMapping("/store")
    public ApiResponse<ReviewResDTO.ReviewPreviewList> getStoreReviews(
            @Valid @RequestBody ReviewReqDTO.GetStoreReviews request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.GET_STORE_REVIEW_SUCCESS,
                reviewService.getStoreReviews(request)
        );
    }

    @PostMapping("/my/id")
    public ApiResponse<ReviewResDTO.CursorPagination> getMyReviewsById(
            @Valid @RequestBody ReviewReqDTO.GetMyReviewsById request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.GET_MY_REVIEWS_BY_ID_SUCCESS,
                reviewService.getMyReviewsById(request)
        );
    }

    @PostMapping("/my/rating")
    public ApiResponse<ReviewResDTO.CursorPagination> getMyReviewsByRating(
            @Valid @RequestBody ReviewReqDTO.GetMyReviewsByRating request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.GET_MY_REVIEWS_BY_RATING_SUCCESS,
                reviewService.getMyReviewsByRating(request)
        );
    }
}