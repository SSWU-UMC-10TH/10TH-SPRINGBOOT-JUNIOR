package com.example.umc10th.domain.user.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

public class UserResDTO {

    @Builder
    public record GetInfo(
            String name,
            Gender gender,
            Date birth_date,
            String nickname,
            String email,
            String phoneNumber,
            String profileUrl,
            LocalDateTime created_at,
            LocalDateTime updated_at,
            LocalDateTime deleted_at,
            Integer point
    ){}

    public enum Gender {
        MALE, FEMALE, NONE
    }
}
