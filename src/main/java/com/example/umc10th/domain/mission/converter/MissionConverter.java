package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.store.entity.Store;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MissionConverter {

    // w7 : 진행 중인 내 미션 조회
    public static MissionResDTO.UserMissionResponse toUserMissionResponse(UserMission userMission) {

        Mission mission = userMission.getMission();
        Store store = mission.getStore();

        int dday = (int) ChronoUnit.DAYS.between(
                LocalDate.now(),
                mission.getEndDate()
        );

        return new MissionResDTO.UserMissionResponse(
                userMission.getId(),
                mission.getId(),
                store.getName(),
                mission.getConditionAmount(),
                mission.getRewardPoint(),
                userMission.getStatus(),
                dday
        );
    }

    public static MissionResDTO.UserMissionListResponse toUserMissionListResponse(
            List<MissionResDTO.UserMissionResponse> missions,
            Long cursor,
            Boolean hasNext
    ) {
        return new MissionResDTO.UserMissionListResponse(
                missions,
                cursor,
                hasNext
        );
    }
}