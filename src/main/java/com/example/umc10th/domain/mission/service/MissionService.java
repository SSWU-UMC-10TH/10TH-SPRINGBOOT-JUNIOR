package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.dto.UserMissionQueryDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exceptions.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    // 홈 화면 조회
    public MissionResDTO.MissionHomeResponse getHome(
            Long userId,
            Long regionId,
            Long cursor,
            Integer size
    ) {
        Pageable pageable = PageRequest.of(0, size + 1);

        List<Mission> missionList =
                missionRepository.findHomeMissionsByRegionWithCursor(
                        userId,
                        regionId,
                        cursor,
                        pageable
                );

        boolean hasNext = missionList.size() > size;

        if (hasNext) {
            missionList = missionList.subList(0, size);
        }

        List<MissionResDTO.HomeMissionResponse> missions = missionList.stream()
                .map(mission -> {
                    Store store = mission.getStore();

                    int dday = (int) ChronoUnit.DAYS.between(
                            LocalDate.now(),
                            mission.getEndDate()
                    );

                    return new MissionResDTO.HomeMissionResponse(
                            mission.getId(),
                            store.getName(),
                            store.getCategory().name(),
                            mission.getConditionAmount(),
                            mission.getRewardPoint(),
                            dday
                    );
                })
                .toList();

        Long nextCursor = missions.isEmpty()
                ? null
                : missions.get(missions.size() - 1).missionId();

        String regionName = missionList.isEmpty()
                ? null
                : missionList.get(0).getStore().getRegion().getName();

        Integer totalMissionCount =
                missionRepository.countTotalMissionsByRegion(regionId);

        Integer completedMissionCount =
                missionRepository.countCompletedMissionsByUserAndRegion(
                        userId,
                        regionId
                );

        return new MissionResDTO.MissionHomeResponse(
                regionName,
                completedMissionCount,
                totalMissionCount,
                missions,
                nextCursor,
                hasNext
        );
    }

    // 사용자별 진행중/진행 완료 미션 조회
    public MissionResDTO.UserMissionListResponse getMission(
            Long userId,
            Status status,
            Long cursor,
            Integer size
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProjectException(UserErrorCode.USER_NOT_FOUND));

        Pageable pageable = PageRequest.of(0, size + 1);

        List<UserMissionQueryDTO> userMissionList =
                userMissionRepository.findMyMissionsByStatusWithCursor(
                        user.getId(),
                        status,
                        cursor,
                        pageable
                );

        boolean hasNext = userMissionList.size() > size;

        if (hasNext) {
            userMissionList = userMissionList.subList(0, size);
        }

        List<MissionResDTO.UserMissionResponse> missions =
                userMissionList.stream()
                        .map(MissionConverter::toUserMissionResponseFromQuery)
                        .toList();

        Long nextCursor = missions.isEmpty()
                ? null
                : missions.get(missions.size() - 1).userMissionId();

        return MissionConverter.toUserMissionListResponse(missions, nextCursor, hasNext);
    }

    // w7 : 진행 중 미션 조회 (오프셋)
    public MissionResDTO.PageResponse<MissionResDTO.UserMissionResponse> getMyInProgressMissions(
            Long userId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ProjectException(UserErrorCode.USER_NOT_FOUND));

        Sort sortInfo = sort != null
                ? Sort.by(sort).descending()
                : Sort.by("id").descending();

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

//      Page<UserMission> page =
        Page<UserMissionQueryDTO> page =
                userMissionRepository.findAllByUserIdAndStatusWithPaging(
                        userId,
                        Status.IN_PROGRESS,
                        pageRequest
                );

        List<MissionResDTO.UserMissionResponse> missions =
                page.getContent().stream()
                        .map(MissionConverter::toUserMissionResponseFromQuery)
                        .toList();

        return new MissionResDTO.PageResponse<>(
                missions,
                page.getNumber(),
                page.getSize()
        );
    }



    public MissionResDTO.MissionStatusUpdateResponse patchCompleted(
            MissionReqDTO.MissionStatusUpdateRequest dto
    ) {
        throw new UnsupportedOperationException("아직 구현되지 않은 기능입니다.");
    }
}