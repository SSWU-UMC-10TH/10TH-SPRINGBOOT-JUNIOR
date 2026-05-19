package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "review")
@SQLDelete(sql = "UPDATE review SET deleted_at = NOW() WHERE review_id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Lob
    @Column(name = "review_comment", nullable = false)
    private String reviewComment;

    @Column(name = "review_title", nullable = false)
    private String reviewTitle;

    @Column(name = "rating", nullable = false)
    private Double rating;

    /*
     * FK: user_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /*
     * FK: store_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}