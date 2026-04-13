package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.status;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 홈 화면 조회
    @PostMapping("/v1/home")
    public ApiResponse<MissionResDTO.GetHome> getHome(
            @RequestBody MissionReqDTO.GetHome dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.HOME_OK;
        return ApiResponse.onSuccess(code, missionService.getHome(dto));
    }

    // 사용자별 진행중/진행 완료 미션 조회
    @PostMapping("/v1/missions")
    public ApiResponse<MissionResDTO.GetMission> getMissions(
            @RequestParam status status,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_OK;
        return ApiResponse.onSuccess(code, missionService.getMission(status, cursor, size));
    }

    // 미션 성공 COMPLETED 처리
    @PostMapping("v1/completed")
    public ApiResponse<MissionResDTO.CompletedMissionStatus> patchCompleted(
            @RequestBody MissionReqDTO.CompletedMissionStatus dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_OK;
        return ApiResponse.onSuccess(code, missionService.patchCompleted(dto));
    }
}
