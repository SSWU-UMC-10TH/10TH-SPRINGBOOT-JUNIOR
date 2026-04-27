package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.entity.mapping.UserFoodPreference;
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
@Table(name = "food_category")
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    // 연관 관계
    @OneToMany(mappedBy = "foodCategory")
    private List<UserFoodPreference> foodPreferenceList = new ArrayList<>();
}
