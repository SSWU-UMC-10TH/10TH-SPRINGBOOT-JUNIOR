package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 성공 조건
    @Column(name = "condition_amount", nullable = false)
    private int conditionAmount;

    // 성공 보상
    @Column(name = "reward_point", nullable = false)
    private int rewardPoint;

    // 미션 세부 내용
    @Column(name = "content")
    private String content;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    // 연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
