package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
