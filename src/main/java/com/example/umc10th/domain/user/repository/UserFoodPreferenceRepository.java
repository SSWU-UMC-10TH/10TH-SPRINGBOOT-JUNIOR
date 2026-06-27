package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.mapping.UserFoodPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFoodPreferenceRepository extends JpaRepository<UserFoodPreference, Long> {
}