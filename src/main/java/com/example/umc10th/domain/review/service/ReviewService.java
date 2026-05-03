package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;

    public ReviewResDTO.CreateReview createReview(
            Long userMissionId,
            ReviewReqDTO.CreateReview dto,
            List<MultipartFile> images
    ) {
        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.MISSION_COMPLETED_NOT_FOUND));

        // 별점은 0 이상 5 이하
        if (dto.rating() == null || dto.rating() < 0 || dto.rating() > 5) {
            throw new ReviewException(ReviewErrorCode.INVALID_RATING);
        }
        User user = userMission.getUser();
        Store store = userMission.getMission().getStore();

        Review review = Review.builder()
                .content(dto.content())
                .rating(dto.rating())
                .user(user)
                .store(store)
                .build();

        Review savedReview = reviewRepository.save(review);

        List<String> imageUrls = new ArrayList<>();

        return ReviewResDTO.CreateReview.builder()
                .review_id(savedReview.getId())
                .user_mission_id(userMission.getId())
                .store_name(store.getName())
                .rating(savedReview.getRating())
                .content(savedReview.getContent())
                .imageUrls(imageUrls)
                .build();
    }

    // w7 : 내가 작성한 리뷰 조회 - ID 순
    public ReviewResDTO.MyReviewListResponse getMyReviewsById(
            Long userId,
            Long cursor,
            Integer size
    ) {
        Pageable pageable = PageRequest.of(0, size + 1);

        List<Review> reviewList =
                reviewRepository.findMyReviewsByIdCursor(userId, cursor, pageable);

        boolean hasNext = reviewList.size() > size;

        if (hasNext) {
            reviewList = reviewList.subList(0, size);
        }

        List<ReviewResDTO.MyReviewResponse> reviews =
                reviewList.stream()
                        .map(ReviewConverter::toMyReviewResponse)
                        .toList();

        Long nextCursor = reviews.isEmpty()
                ? null
                : reviews.get(reviews.size() - 1).reviewId();

        return new ReviewResDTO.MyReviewListResponse(
                reviews,
                nextCursor,
                hasNext
        );
    }

    // w7 : 내가 작성한 리뷰 조회 - 별점 순
    public ReviewResDTO.MyReviewRatingListResponse getMyReviewsByRating(
            Long userId,
            Double ratingCursor,
            Long reviewIdCursor,
            Integer size
    ) {
        Pageable pageable = PageRequest.of(0, size + 1);

        List<Review> reviewList =
                reviewRepository.findMyReviewsByRatingCursor(
                        userId,
                        ratingCursor,
                        reviewIdCursor,
                        pageable
                );

        boolean hasNext = reviewList.size() > size;

        if (hasNext) {
            reviewList = reviewList.subList(0, size);
        }

        List<ReviewResDTO.MyReviewResponse> reviews =
                reviewList.stream()
                        .map(ReviewConverter::toMyReviewResponse)
                        .toList();

        Double nextRatingCursor = reviews.isEmpty()
                ? null
                : reviews.get(reviews.size() - 1).rating();

        Long nextReviewIdCursor = reviews.isEmpty()
                ? null
                : reviews.get(reviews.size() - 1).reviewId();

        return new ReviewResDTO.MyReviewRatingListResponse(
                reviews,
                nextRatingCursor,
                nextReviewIdCursor,
                hasNext
        );
    }
}
