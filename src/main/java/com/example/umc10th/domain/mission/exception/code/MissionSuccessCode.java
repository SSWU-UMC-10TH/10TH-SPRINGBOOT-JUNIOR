package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_MY_MISSION_SUCCESS(HttpStatus.OK, "MISSION200_1", "내 미션 목록 조회에 성공했습니다."),
    CHALLENGE_MISSION_SUCCESS(HttpStatus.OK, "MISSION200_2", "미션 도전에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
