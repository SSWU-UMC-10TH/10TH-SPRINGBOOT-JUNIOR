package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.user.entity.mapping.UserRegionProgress;
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
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reward_threshold", nullable = false)
    private Long rewardThreshold;

    @Column(name = "reward_point", nullable = false)
    private Long rewardPoint;

    // 연관 관계
    @OneToMany
    private List<UserRegionProgress> progressList = new ArrayList<>();
}
