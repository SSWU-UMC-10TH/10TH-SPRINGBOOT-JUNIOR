package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    HOME_OK(HttpStatus.OK,
            "HOME200_1",
            "성공적으로 사용자/지역별 홈 화면을 조회했습니다."),

    MISSION_OK(HttpStatus.OK,
            "MISSION200_1",
            "사용자별 진행중/진행 완료 미션을 성공적으로 조회했습니다.")
    ;



    private final HttpStatus status;
    private final String code;
    private final String message;
}
