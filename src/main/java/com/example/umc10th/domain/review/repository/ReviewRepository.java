package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"user"})
    @Query("""
            select r
            from Review r
            where r.store.storeId = :storeId
            and r.deletedAt is null
            order by r.createdAt desc
            """)
    Page<Review> findStoreReviews(
            @Param("storeId") Long storeId,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"store"})
    @Query("""
            select r
            from Review r
            where r.user.userId = :userId
            and r.deletedAt is null
            and (:cursorId is null or r.reviewId < :cursorId)
            order by r.reviewId desc
            """)
    Slice<Review> findMyReviewsByIdCursor(
            @Param("userId") Long userId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"store"})
    @Query("""
            select r
            from Review r
            where r.user.userId = :userId
            and r.deletedAt is null
            and (
                :cursorRating is null
                or r.rating < :cursorRating
                or (r.rating = :cursorRating and r.reviewId < :cursorId)
            )
            order by r.rating desc, r.reviewId desc
            """)
    Slice<Review> findMyReviewsByRatingCursor(
            @Param("userId") Long userId,
            @Param("cursorRating") Double cursorRating,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}