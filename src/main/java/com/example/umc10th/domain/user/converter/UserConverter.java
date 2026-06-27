package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

    public static UserResDTO.SignUp toSignUpResult(User user) {
        return UserResDTO.SignUp.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }

    public static UserResDTO.Login toLoginResult(String accessToken) {
        return UserResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }

    public static UserResDTO.MyPage toMyPage(User user) {
        return UserResDTO.MyPage.builder()
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isPhoneVerified(user.getPhoneNumber() != null && !user.getPhoneNumber().isBlank())
                .point(user.getPoint())
                .build();
    }
}