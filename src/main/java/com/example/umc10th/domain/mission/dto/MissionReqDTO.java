package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MissionReqDTO {

    // 홈 조회 요청
    public record MissionHomeRequest(

            @NotNull(message = "사용자 ID는 필수입니다.")
            @Positive(message = "사용자 ID는 양수여야 합니다.")
            Long userId,

            @NotNull(message = "지역 ID는 필수입니다.")
            @Positive(message = "지역 ID는 양수여야 합니다.")
            Long regionId
    ) {}

    // 사용자별 진행중/진행 완료 미션 조회 요청
    public record UserMissionRequest(

            @NotNull(message = "사용자 ID는 필수입니다.")
            @Positive(message = "사용자 ID는 양수여야 합니다.")
            Long userId
    ) {}

    // w7 : 진행 중인 내 미션 조회 요청
    public record MyMissionRequest(

            @NotNull(message = "사용자 ID는 필수입니다.")
            @Positive(message = "사용자 ID는 양수여야 합니다.")
            Long userId
    ) {}

    // 미션 성공 completed 처리 요청
    public record MissionStatusUpdateRequest(

            @NotNull(message = "사용자 ID는 필수입니다.")
            @Positive(message = "사용자 ID는 양수여야 합니다.")
            Long userId,

            @NotNull(message = "사용자 미션 ID는 필수입니다.")
            @Positive(message = "사용자 미션 ID는 양수여야 합니다.")
            Long missionCompletedId,

            @NotNull(message = "미션 상태는 필수입니다.")
            Status status
    ) {}
}