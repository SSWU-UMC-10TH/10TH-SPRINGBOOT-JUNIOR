package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 식당에서 진행하는 미션 목록 조회
    public record GetStoreMissions(
            List<GetStoreMission> missionList,
            Integer listSize,
            Boolean hasNext,
            Long nextCursorId
    ) {
    }

    // 식당에서 진행하는 미션 1개 조회
    public record GetStoreMission(
            Long missionId,
            Long storeId,
            String storeName,
            String title,
            Integer point,
            Integer targetAmount,
            Boolean isActive,
            LocalDateTime createdAt
    ) {
    }

    // 사용자가 진행 중인 미션 목록 조회
    public record GetMyMissions(
            List<GetMyMission> missionList,
            Integer listSize,
            Boolean hasNext,
            Long nextCursorId
    ) {
    }

    // 사용자가 진행 중인 미션 1개 조회
    public record GetMyMission(
            Long missionChoiceId,
            Long missionId,
            Long userId,
            String storeName,
            String title,
            Integer point,
            Integer targetAmount,
            Boolean success,
            LocalDateTime startedAt,
            LocalDateTime successAt
    ) {
    }

    // 미션 도전 요청
    public record ChallengeMission(
            Long missionChoiceId,
            Long missionId,
            Long userId,
            Boolean success,
            LocalDateTime startedAt
    ) {
    }
}