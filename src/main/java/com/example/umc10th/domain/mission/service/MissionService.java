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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public MissionResDTO.MissionPreviewList getMyMissions(MissionReqDTO.GetMyMissions request) {
        PageRequest pageRequest = PageRequest.of(request.page(), 10);

        Page<MissionChoice> missionChoicePage = missionChoiceRepository.findMyMissions(
                request.userId(),
                pageRequest
        );

        return MissionConverter.toMissionPreviewList(missionChoicePage);
    }

    @Transactional
    public MissionResDTO.ChallengeMission challengeMission(MissionReqDTO.ChallengeMission request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Boolean alreadyChallenged = missionChoiceRepository.existsByUserUserIdAndMissionMissionId(
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