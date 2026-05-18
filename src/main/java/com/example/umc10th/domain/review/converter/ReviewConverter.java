package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.CreateReview toCreateReview(Review review) {
        return new ReviewResDTO.CreateReview(
                review.getReviewId(),
                review.getUser().getUserId(),
                review.getStore().getStoreId(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt()
        );
    }

    public static ReviewResDTO.ReviewPreview toReviewPreview(Review review) {
        return new ReviewResDTO.ReviewPreview(
                review.getReviewId(),
                review.getUser().getNickname(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt()
        );
    }

    public static ReviewResDTO.ReviewPreviewList toReviewPreviewList(Page<Review> reviewPage) {
        List<ReviewResDTO.ReviewPreview> reviewList = reviewPage.stream()
                .map(ReviewConverter::toReviewPreview)
                .toList();

        return new ReviewResDTO.ReviewPreviewList(
                reviewList,
                reviewList.size(),
                reviewPage.getTotalPages(),
                reviewPage.getTotalElements(),
                reviewPage.isFirst(),
                reviewPage.isLast()
        );
    }

    public static ReviewResDTO.MyReview toMyReview(Review review) {
        return new ReviewResDTO.MyReview(
                review.getReviewId(),
                review.getStore().getStoreName(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt()
        );
    }

    public static ReviewResDTO.CursorPagination toCursorPagination(Slice<Review> reviewSlice) {
        List<ReviewResDTO.MyReview> reviewList = reviewSlice.stream()
                .map(ReviewConverter::toMyReview)
                .toList();

        Long nextCursorId = null;
        Double nextCursorRating = null;

        if (reviewSlice.hasNext() && !reviewSlice.getContent().isEmpty()) {
            Review lastReview = reviewSlice.getContent().get(reviewSlice.getNumberOfElements() - 1);
            nextCursorId = lastReview.getReviewId();
            nextCursorRating = lastReview.getRating();
        }

        return new ReviewResDTO.CursorPagination(
                reviewList,
                reviewList.size(),
                reviewSlice.hasNext(),
                nextCursorId,
                nextCursorRating
        );
    }
}