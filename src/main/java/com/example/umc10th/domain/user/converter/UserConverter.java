package com.example.umc10th.domain.user.converter;


import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.Converter;

@Converter
public class UserConverter {
    public static UserResDTO.GetMyPage toGetMyPage(User user) {
        return UserResDTO.GetMyPage.builder()
                .nickname(user.getNickname())
                .profileUrl(user.getProfileUrl())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .point(user.getPoint())
                .build();
    }
}
