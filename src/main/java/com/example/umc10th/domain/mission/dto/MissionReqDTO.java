package com.example.umc10th.domain.mission.dto;

public class MissionReqDTO {

    public record GetMyMissions(
            Long userId,
            Integer page
    ) {
    }

    public record ChallengeMission(
            Long userId,
            Long missionId
    ) {
    }
}
