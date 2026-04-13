package com.example.umc10th.domain.mission.dto;

public class MissionReqDTO {

    // 홈 조회
    public record GetHome(
            Long user_id,
            Long region_id
    ) {}
}
