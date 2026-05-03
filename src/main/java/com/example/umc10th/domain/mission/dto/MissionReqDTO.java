package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.Status;

public class MissionReqDTO {

    // 홈 조회
    public record GetHome(
            Long user_id,
            Long region_id
    ) {}

    // 사용자별 진행중/진행 완료 미션 조회
    public record GetMission(
            Long user_id
    ) {}

    // 미션 성공 completed 처리 (patch)
    public record CompletedMissionStatus(
            Long user_id,
            Long mission_completed_id,
            Status status // COMPLETED
    ) {}
}
