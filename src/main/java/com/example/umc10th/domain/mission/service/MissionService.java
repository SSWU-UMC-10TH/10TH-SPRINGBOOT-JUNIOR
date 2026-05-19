package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MissionChoice;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionChoiceRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionChoiceRepository missionChoiceRepository;
    private final UserRepository userRepository;

    // 식당에서 진행하는 미션 목록 조회(cursor)
    public MissionResDTO.GetStoreMissions getStoreMissions(
            MissionReqDTO.GetStoreMissions request
    ) {
        PageRequest pageRequest = PageRequest.of(0, request.size());

        Slice<Mission> missionSlice = missionRepository.findStoreMissions(
                request.storeId(),
                request.cursorId(),
                pageRequest
        );

        return MissionConverter.toGetStoreMissions(missionSlice);
    }

    // 식당에서 진행하는 미션 1개 상세 조회
    public MissionResDTO.GetStoreMission getStoreMission(
            MissionReqDTO.GetStoreMission request
    ) {
        Mission mission = missionRepository.findStoreMission(
                        request.storeId(),
                        request.missionId()
                )
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        return MissionConverter.toGetStoreMission(mission);
    }

    // 사용자가 진행 중인 미션 목록 조회(cursor)
    public MissionResDTO.GetMyMissions getMyMissions(
            MissionReqDTO.GetMyMissions request
    ) {
        PageRequest pageRequest = PageRequest.of(0, request.size());

        Slice<MissionChoice> missionChoiceSlice = missionChoiceRepository.findMyMissions(
                request.userId(),
                request.cursorId(),
                pageRequest
        );

        return MissionConverter.toGetMyMissions(missionChoiceSlice);
    }

    // 사용자가 진행 중인 미션 1개 상세 조회
    public MissionResDTO.GetMyMission getMyMission(
            MissionReqDTO.GetMyMission request
    ) {
        MissionChoice missionChoice = missionChoiceRepository.findMyMission(
                        request.userId(),
                        request.missionChoiceId()
                )
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        return MissionConverter.toGetMyMission(missionChoice);
    }

    // 미션 도전 요청
    @Transactional
    public MissionResDTO.ChallengeMission challengeMission(
            MissionReqDTO.ChallengeMission request
    ) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Boolean alreadyChallenged =
                missionChoiceRepository.existsByUserUserIdAndMissionMissionId(
                        request.userId(),
                        request.missionId()
                );

        if (alreadyChallenged) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGED);
        }

        MissionChoice missionChoice = MissionChoice.builder()
                .user(user)
                .mission(mission)
                .startedAt(LocalDateTime.now())
                .success(false)
                .build();

        missionChoiceRepository.save(missionChoice);

        return MissionConverter.toChallengeMission(missionChoice);
    }
}