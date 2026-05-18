package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.CreateReview createReview(ReviewReqDTO.CreateReview request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(request.content())
                .rating(request.rating())
                .build();

        reviewRepository.save(review);

        return ReviewConverter.toCreateReview(review);
    }

    public ReviewResDTO.ReviewPreviewList getStoreReviews(ReviewReqDTO.GetStoreReviews request) {
        PageRequest pageRequest = PageRequest.of(request.page(), 10);

        Page<Review> reviewPage = reviewRepository.findStoreReviews(
                request.storeId(),
                pageRequest
        );

        return ReviewConverter.toReviewPreviewList(reviewPage);
    }

    public ReviewResDTO.CursorPagination getMyReviewsById(
            ReviewReqDTO.GetMyReviewsById request
    ) {
        PageRequest pageRequest = PageRequest.of(0, request.size());

        Slice<Review> reviewSlice = reviewRepository.findMyReviewsByIdCursor(
                request.userId(),
                request.cursorId(),
                pageRequest
        );

        return ReviewConverter.toCursorPagination(reviewSlice);
    }

    public ReviewResDTO.CursorPagination getMyReviewsByRating(
            ReviewReqDTO.GetMyReviewsByRating request
    ) {
        PageRequest pageRequest = PageRequest.of(0, request.size());

        Slice<Review> reviewSlice = reviewRepository.findMyReviewsByRatingCursor(
                request.userId(),
                request.cursorRating(),
                request.cursorId(),
                pageRequest
        );

        return ReviewConverter.toCursorPagination(reviewSlice);
    }
}