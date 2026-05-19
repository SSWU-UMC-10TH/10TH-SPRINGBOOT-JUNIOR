package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.util.List;

public class ReviewConverter {

    // 리뷰 작성 성공 응답
    public static ReviewResDTO.CreateReview toCreateReview(Review review) {
        return new ReviewResDTO.CreateReview(
                review.getReviewId(),
                review.getUser().getUserId(),
                review.getStore().getStoreId(),
                review.getReviewTitle(),
                review.getReviewComment(),
                review.getRating(),
                review.getCreatedAt()
        );
    }

    // 식당 리뷰 목록에서 보여줄 리뷰 1개
    public static ReviewResDTO.GetStoreReview toGetStoreReview(Review review) {
        return new ReviewResDTO.GetStoreReview(
                review.getReviewId(),
                review.getUser().getUserId(),
                review.getUser().getNickname(),
                review.getStore().getStoreId(),
                review.getStore().getStoreName(),
                review.getReviewTitle(),
                review.getReviewComment(),
                review.getRating(),
                review.getCreatedAt()
        );
    }

    // 식당 리뷰 목록 리스트 조회(cursor)
    public static ReviewResDTO.GetStoreReviews toGetStoreReviews(Slice<Review> reviewSlice) {
        List<ReviewResDTO.GetStoreReview> reviewList = reviewSlice.stream()
                .map(ReviewConverter::toGetStoreReview)
                .toList();

        Long nextCursorId = null;

        if (reviewSlice.hasNext() && !reviewSlice.getContent().isEmpty()) {
            Review lastReview = reviewSlice.getContent()
                    .get(reviewSlice.getNumberOfElements() - 1);

            nextCursorId = lastReview.getReviewId();
        }

        return new ReviewResDTO.GetStoreReviews(
                reviewList,
                reviewList.size(),
                reviewSlice.hasNext(),
                nextCursorId
        );
    }

    // 내가 작성한 리뷰 1개
    public static ReviewResDTO.GetMyReview toGetMyReview(Review review) {
        return new ReviewResDTO.GetMyReview(
                review.getReviewId(),
                review.getUser().getUserId(),
                review.getUser().getNickname(),
                review.getStore().getStoreId(),
                review.getStore().getStoreName(),
                review.getReviewTitle(),
                review.getReviewComment(),
                review.getRating(),
                review.getCreatedAt()
        );
    }

    // 내가 쓴 리뷰 조회 - 최신순(cursor)
    public static ReviewResDTO.GetMyReviewsById toGetMyReviewsById(Slice<Review> reviewSlice) {
        List<ReviewResDTO.GetMyReview> reviewList = reviewSlice.stream()
                .map(ReviewConverter::toGetMyReview)
                .toList();

        Long nextCursorId = null;

        if (reviewSlice.hasNext() && !reviewSlice.getContent().isEmpty()) {
            Review lastReview = reviewSlice.getContent()
                    .get(reviewSlice.getNumberOfElements() - 1);

            nextCursorId = lastReview.getReviewId();
        }

        return new ReviewResDTO.GetMyReviewsById(
                reviewList,
                reviewList.size(),
                reviewSlice.hasNext(),
                nextCursorId
        );
    }

    // 내가 쓴 리뷰 조회 - 평점순(cursor)
    public static ReviewResDTO.GetMyReviewsByRating toGetMyReviewsByRating(Slice<Review> reviewSlice) {
        List<ReviewResDTO.GetMyReview> reviewList = reviewSlice.stream()
                .map(ReviewConverter::toGetMyReview)
                .toList();

        Long nextCursorId = null;
        Double nextCursorRating = null;

        if (reviewSlice.hasNext() && !reviewSlice.getContent().isEmpty()) {
            Review lastReview = reviewSlice.getContent()
                    .get(reviewSlice.getNumberOfElements() - 1);

            nextCursorId = lastReview.getReviewId();
            nextCursorRating = lastReview.getRating();
        }

        return new ReviewResDTO.GetMyReviewsByRating(
                reviewList,
                reviewList.size(),
                reviewSlice.hasNext(),
                nextCursorId,
                nextCursorRating
        );
    }
}