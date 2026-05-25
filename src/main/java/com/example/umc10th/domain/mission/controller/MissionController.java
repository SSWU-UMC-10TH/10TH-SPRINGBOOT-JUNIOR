package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionService missionService;

    // 식당에서 진행 중인 미션 목록 조회(cursor)
    @PostMapping("/store")
    public ApiResponse<MissionResDTO.GetStoreMissions> getStoreMissions(
            @Valid @RequestBody MissionReqDTO.GetStoreMissions request
    ) {

        return ApiResponse.onSuccess(
                MissionSuccessCode.GET_STORE_MISSIONS_SUCCESS,
                missionService.getStoreMissions(request)
        );
    }

    // 식당에서 진행 중인 미션 1개 상세 조회
    @PostMapping("/store/mission")
    public ApiResponse<MissionResDTO.GetStoreMission> getStoreMission(
            @Valid @RequestBody MissionReqDTO.GetStoreMission request
    ) {

        return ApiResponse.onSuccess(
                MissionSuccessCode.GET_STORE_MISSION_SUCCESS,
                missionService.getStoreMission(request)
        );
    }

    // 사용자가 진행 중인 미션 목록 조회(cursor)
    @PostMapping("/my")
    public ApiResponse<MissionResDTO.GetMyMissions> getMyMissions(
            @Valid @RequestBody MissionReqDTO.GetMyMissions request
    ) {

        return ApiResponse.onSuccess(
                MissionSuccessCode.GET_MY_MISSIONS_SUCCESS,
                missionService.getMyMissions(request)
        );
    }

    // 사용자가 진행 중인 미션 1개 상세 조회
    @PostMapping("/my/mission")
    public ApiResponse<MissionResDTO.GetMyMission> getMyMission(
            @Valid @RequestBody MissionReqDTO.GetMyMission request
    ) {

        return ApiResponse.onSuccess(
                MissionSuccessCode.GET_MY_MISSION_SUCCESS,
                missionService.getMyMission(request)
        );
    }

    // 미션 도전 요청
    @PostMapping("/challenge")
    public ApiResponse<MissionResDTO.ChallengeMission> challengeMission(
            @Valid @RequestBody MissionReqDTO.ChallengeMission request
    ) {

        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGE_MISSION_SUCCESS,
                missionService.challengeMission(request)
        );
    }
}