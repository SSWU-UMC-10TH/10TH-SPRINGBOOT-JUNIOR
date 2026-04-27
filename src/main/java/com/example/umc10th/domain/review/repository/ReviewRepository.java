package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
            select r
            from Review r
            join fetch r.user u
            where r.store.storeId = :storeId
            and r.deletedAt is null
            order by r.createdAt desc
            """)
    Page<Review> findStoreReviews(
            @Param("storeId") Long storeId,
            Pageable pageable
    );
}