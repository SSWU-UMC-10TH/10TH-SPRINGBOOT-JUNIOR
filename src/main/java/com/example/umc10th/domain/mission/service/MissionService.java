package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionResDTO.GetHome getHome(MissionReqDTO.GetHome dto) {
        throw new UnsupportedOperationException("아직 구현되지 않은 기능입니다.");
    }

    public MissionResDTO.GetMission getMission(Status status, Long cursor, Integer size) {
        throw new UnsupportedOperationException("아직 구현되지 않은 기능입니다.");
    }

    public MissionResDTO.CompletedMissionStatus patchCompleted(MissionReqDTO.CompletedMissionStatus dto) {
        throw new UnsupportedOperationException("아직 구현되지 않은 기능입니다.");
    }
}
