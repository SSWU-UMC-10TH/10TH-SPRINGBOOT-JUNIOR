package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    private final MissionService missionService;

    // 홈 화면 조회
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.MissionHomeResponse> getHome(
            @Valid @PathVariable Long userId,
            @RequestParam Long regionId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        BaseSuccessCode code = MissionSuccessCode.HOME_OK;
        return ApiResponse.onSuccess(code, missionService.getHome(userId, regionId, cursor, size));
    }

    // 사용자별 진행중/진행 완료 미션 조회
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.UserMissionListResponse> getMissions(
            @Valid @PathVariable Long userId,
            @RequestParam Status status,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_OK;
        return ApiResponse.onSuccess(
                code,
                missionService.getMission(userId, status, cursor, size)
        );
    }

    // 미션 성공 COMPLETED 처리
    @PatchMapping("/completed")
    public ApiResponse<MissionResDTO.MissionStatusUpdateResponse> patchCompleted(
            @Valid @RequestBody MissionReqDTO.MissionStatusUpdateRequest dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_OK;
        return ApiResponse.onSuccess(code, missionService.patchCompleted(dto));
    }

    // w7 : 진행 중 미션 조회 (오프셋)
    @GetMapping("/missions/in-progress")
    public ApiResponse<MissionResDTO.PageResponse<MissionResDTO.UserMissionResponse>> getMyInProgressMissions(
            @PathVariable Long userId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMyInProgressMissions(
                        userId,
                        pageSize,
                        pageNumber,
                        sort
                )
        );
    }
}