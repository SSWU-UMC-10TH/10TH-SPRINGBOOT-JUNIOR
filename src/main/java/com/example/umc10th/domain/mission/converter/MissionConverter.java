package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.store.entity.Store;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MissionConverter {
    public static MissionResDTO.GetMissionItem toGetMissionItem(UserMission userMission) {

        Mission mission = userMission.getMission();
        Store store = mission.getStore();

        int dday = (int) ChronoUnit.DAYS.between(
                LocalDate.now(),
                mission.getEndDate()
        );

        return MissionResDTO.GetMissionItem.builder()
                .user_mission_id(userMission.getId())
                .mission_id(mission.getId())
                .store_name(store.getName())
                .condition_amount(mission.getConditionAmount())
                .reward_point(mission.getRewardPoint())
                .status(userMission.getStatus())
                .dday(dday)
                .build();
    }

    public static MissionResDTO.GetMission toGetMission(
            List<MissionResDTO.GetMissionItem> missions,
            Long cursor,
            boolean hasNext
    ) {
        return MissionResDTO.GetMission.builder()
                .missions(missions)
                .cursor(cursor)
                .hasNext(hasNext)
                .build();
    }
}
