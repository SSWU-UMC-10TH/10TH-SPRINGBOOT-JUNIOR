package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.util.List;

public class MissionResDTO {

    // 홈 화면의 지역별 미션 조회
    @Builder
    public record GetHomeMission (
            Long missionId,
            String storeName,
            String category,
            Integer conditionAmount,
            Integer rewardPoint,
            Integer dday
    ) {}

    // 홈 조회
    @Builder
    public record GetHome (
            // 선택된 지역 이름 ex."안암동"
            String regionName,

            // "안암동에서 사용자가 수행한 미션 개수
            Integer completedMissionCount,

            // "안암동"에서 사용자가 이만큼 수행하면 포인트 제공하는 미션 개수
            Integer totalMissionCount,

            // "안암동"에서 사용자가 아직 수행하지 않은 미션 목록들
            List<GetHomeMission> missions
    ) {}
}
