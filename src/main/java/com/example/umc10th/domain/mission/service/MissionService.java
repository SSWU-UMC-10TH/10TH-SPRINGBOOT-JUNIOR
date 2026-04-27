package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MissionCompleted;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MissionCompletedRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionCompletedRepository missionCompletedRepository;

    public MissionResDTO.GetHome getHome(MissionReqDTO.GetHome dto, Long cursor, Integer size) {

        Pageable pageable = PageRequest.of(0, size + 1);

        List<Mission> missionList =
                missionRepository.findHomeMissionsByRegionWithCursor(
                        dto.user_id(),
                        dto.region_id(),
                        cursor,
                        pageable
                );

        boolean hasNext = missionList.size() > size;

        if (hasNext) {
            missionList = missionList.subList(0, size);
        }

        List<MissionResDTO.GetHomeMission> missions = missionList.stream()
                .map(mission -> {
                    Store store = mission.getStore();

                    int dday = (int) ChronoUnit.DAYS.between(
                            LocalDate.now(),
                            mission.getEndDate()
                    );

                    return MissionResDTO.GetHomeMission.builder()
                            .missionId(mission.getId())
                            .storeName(store.getName())
                            .category(store.getCategory().name())
                            .conditionAmount(mission.getConditionAmount())
                            .rewardPoint(mission.getRewardPoint())
                            .dday(dday)
                            .build();
                })
                .toList();

        Long nextCursor = missions.isEmpty()
                ? null
                : missions.get(missions.size() - 1).missionId();

        String regionName = missionList.isEmpty()
                ? null
                : missionList.get(0).getStore().getRegion().getName();

        Integer totalMissionCount =
                missionRepository.countTotalMissionsByRegion(dto.region_id());

        Integer completedMissionCount =
                missionRepository.countCompletedMissionsByUserAndRegion(
                        dto.user_id(),
                        dto.region_id()
                );

        return MissionResDTO.GetHome.builder()
                .regionName(regionName)
                .completedMissionCount(completedMissionCount)
                .totalMissionCount(totalMissionCount)
                .missions(missions)
                .cursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    public MissionResDTO.GetMission getMission(Long userId, Status status, Long cursor, Integer size) {

        Pageable pageable = PageRequest.of(0, size + 1);

        List<MissionCompleted> missionCompletedList =
                missionCompletedRepository.findMyMissionsByStatusWithCursor(
                        userId,
                        status,
                        cursor,
                        pageable
                );

        boolean hasNext = missionCompletedList.size() > size;

        if (hasNext) {
            missionCompletedList = missionCompletedList.subList(0, size);
        }

        List<MissionResDTO.GetMissionItem> missions = missionCompletedList.stream()
                .map(missionCompleted -> {
                    Mission mission = missionCompleted.getMission();
                    Store store = mission.getStore();

                    int dday = (int) ChronoUnit.DAYS.between(
                            LocalDate.now(),
                            mission.getEndDate()
                    );

                    return MissionResDTO.GetMissionItem.builder()
                            .user_mission_id(missionCompleted.getId())
                            .mission_id(mission.getId())
                            .store_name(store.getName())
                            .condition_amount(mission.getConditionAmount())
                            .reward_point(mission.getRewardPoint())
                            .status(missionCompleted.getStatus())
                            .dday(dday)
                            .build();
                })
                .toList();

        Long nextCursor = missions.isEmpty()
                ? null
                : missions.get(missions.size() - 1).user_mission_id();

        return MissionResDTO.GetMission.builder()
                .missions(missions)
                .cursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    public MissionResDTO.CompletedMissionStatus patchCompleted(MissionReqDTO.CompletedMissionStatus dto) {
        throw new UnsupportedOperationException("아직 구현되지 않은 기능입니다.");
    }
}