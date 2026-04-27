package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/my")
    public ApiResponse<MissionResDTO.MissionPreviewList> getMyMissions(
            @RequestBody MissionReqDTO.GetMyMissions request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.GET_MY_MISSION_SUCCESS,
                missionService.getMyMissions(request)
        );
    }

    @PostMapping("/challenge")
    public ApiResponse<MissionResDTO.ChallengeMission> challengeMission(
            @RequestBody MissionReqDTO.ChallengeMission request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGE_MISSION_SUCCESS,
                missionService.challengeMission(request)
        );
    }
}
