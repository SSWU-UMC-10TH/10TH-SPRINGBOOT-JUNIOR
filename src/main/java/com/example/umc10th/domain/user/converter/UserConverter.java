package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

    public static UserResDTO.GetMyPage toGetMyPage(User user) {
        return new UserResDTO.GetMyPage(
                user.getNickname(),
                user.getProfileUrl(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPoint()
        );
    }

    public static UserResDTO.SignUp toSignUp(User user) {
        return new UserResDTO.SignUp(
                user.getUserId(),
                user.getEmail(),
                user.getNickname(),
                user.getCreatedAt()
        );
    }

    public static UserResDTO.Login toLogin(String accessToken) {
        return new UserResDTO.Login(accessToken);
    }
}