package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.enums.Category;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 가게별 인증 번호
    @Column(name = "verification_code", nullable = false)
    private Long verificationCode;

    // 가게 이름
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private Category category;

    // 연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}
