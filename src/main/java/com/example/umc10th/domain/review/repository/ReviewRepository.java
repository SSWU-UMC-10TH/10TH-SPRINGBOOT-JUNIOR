package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // w7 : 내가 쓴 리뷰 조회 - ID 순 커서 기반
    @Query("""
        SELECT r
        FROM Review r
        JOIN FETCH r.user u
        JOIN FETCH r.store s
        WHERE u.id = :userId
          AND (:cursor IS NULL OR r.id < :cursor)
        ORDER BY r.id DESC
    """)
    List<Review> findMyReviewsByIdCursor(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

        // w7 : 내가 쓴 리뷰 조회 - 별점 순 커서 기반
        @Query("""
        SELECT r
        FROM Review r
        JOIN FETCH r.user u
        JOIN FETCH r.store s
        WHERE u.id = :userId
          AND (
              :ratingCursor IS NULL
              OR r.rating < :ratingCursor
              OR (r.rating = :ratingCursor AND r.id < :reviewIdCursor)
          )
        ORDER BY r.rating DESC, r.id DESC
    """)
        List<Review> findMyReviewsByRatingCursor(
                @Param("userId") Long userId,
                @Param("ratingCursor") Double ratingCursor,
                @Param("reviewIdCursor") Long reviewIdCursor,
                Pageable pageable
        );
}
