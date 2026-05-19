package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    // 식당에서 진행하는 미션 목록 조회
    public record GetStoreMissions(

            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,

            Long cursorId,

            @NotNull(message = "조회 개수는 필수입니다.")
            @Min(value = 1, message = "조회 개수는 1 이상이어야 합니다.")
            Integer size
    ) {
    }

    // 식당에서 진행하는 미션 1개 조회
    public record GetStoreMission(

            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,

            @NotNull(message = "미션 ID는 필수입니다.")
            Long missionId
    ) {
    }


    // 사용자가 진행 중인 미션 목록 조회
    public record GetMyMissions(

            @NotNull(message = "사용자 ID는 필수입니다.")
            Long userId,

            Long cursorId,

            @NotNull(message = "조회 개수는 필수입니다.")
            @Min(value = 1, message = "조회 개수는 1 이상이어야 합니다.")
            Integer size
    ) {
    }

    // 사용자가 진행 중인 미션 1개 조회
    public record GetMyMission(

            @NotNull(message = "사용자 ID는 필수입니다.")
            Long userId,

            @NotNull(message = "미션 선택 ID는 필수입니다.")
            Long missionChoiceId
    ) {
    }

    // 미션 도전 요청
    public record ChallengeMission(

            @NotNull(message = "사용자 ID는 필수입니다.")
            Long userId,

            @NotNull(message = "미션 ID는 필수입니다.")
            Long missionId
    ) {
    }
}