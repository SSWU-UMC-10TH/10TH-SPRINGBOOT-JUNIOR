package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionPreview(
            Long missionChoiceId,
            Long missionId,
            String storeName,
            String title,
            Integer point,
            Integer targetAmount,
            Boolean success,
            LocalDateTime startedAt,
            LocalDateTime successAt
    ) {
    }

    @Builder
    public record MissionPreviewList(
            List<MissionPreview> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    @Builder
    public record ChallengeMission(
            Long missionChoiceId,
            Long missionId,
            Long userId,
            Boolean success
    ) {
    }
}
