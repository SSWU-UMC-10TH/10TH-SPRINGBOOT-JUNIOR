package com.example.umc10th.domain.mission.dto;

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
}
