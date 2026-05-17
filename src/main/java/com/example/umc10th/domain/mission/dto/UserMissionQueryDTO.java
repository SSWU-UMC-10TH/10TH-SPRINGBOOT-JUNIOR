package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.Status;

import java.time.LocalDate;

public record UserMissionQueryDTO(
        Long userMissionId,
        String storeName,
        Integer conditionAmount,
        Integer rewardPoint,
        Status status,
        LocalDate endDate
) {}
