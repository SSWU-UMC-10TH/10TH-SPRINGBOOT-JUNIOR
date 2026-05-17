package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 홈 화면 미션 아이템 응답
    public record HomeMissionResponse(
            Long missionId,
            String storeName,
            String category,
            Integer conditionAmount,
            Integer rewardPoint,
            Integer dday
    ) {}

    // w7 : 진행 중인 미션 조회
    public record UserMissionResponse(
            Long userMissionId,
//            Long missionId,
            String storeName,
            Integer conditionAmount,
            Integer rewardPoint,
            Status status,
            Integer dday
    ) {}


    // 오프셋 페이징 응답
    public record PageResponse<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}

    // 홈 조회 응답
    public record MissionHomeResponse(
            String regionName,
            Integer completedMissionCount,
            Integer totalMissionCount,
            List<HomeMissionResponse> missions,
            Long cursor,
            Boolean hasNext
    ) {}

    // 사용자별 진행중/진행 완료 미션 조회 응답
    public record UserMissionListResponse(
            List<UserMissionResponse> missions,
            Long cursor,
            Boolean hasNext
    ) {}


    // 미션 상태 변경 응답
    public record MissionStatusUpdateResponse(
            Long missionId,
            Long missionCompletedId,
            Status status,
            LocalDateTime completedAt,
            LocalDateTime requestedAt
    ) {}
}