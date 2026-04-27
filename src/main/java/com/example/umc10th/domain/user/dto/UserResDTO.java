package com.example.umc10th.domain.user.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

public class UserResDTO {

    @Builder
    public record GetMyPage(
            String nickname,
            String profileUrl,
            String email,
            String phoneNumber,
            Long point
    ) {
    }
}
