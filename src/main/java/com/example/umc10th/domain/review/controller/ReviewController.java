package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @RequestBody ReviewReqDTO.CreateReview request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATE_REVIEW_SUCCESS,
                reviewService.createReview(request)
        );
    }

    @PostMapping("/store")
    public ApiResponse<ReviewResDTO.ReviewPreviewList> getStoreReviews(
            @RequestBody ReviewReqDTO.GetStoreReviews request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.GET_STORE_REVIEW_SUCCESS,
                reviewService.getStoreReviews(request)
        );
    }
}