package com.example.umc10th.domain.user.dto;

public class UserReqDTO {

    // 마이페이지
    public record GetMyPage(
            Long userId
    ) {
    }
}
