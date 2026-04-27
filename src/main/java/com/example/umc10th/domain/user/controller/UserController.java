package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exceptions.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/v1/users/signup")
    public ApiResponse<UserResDTO.SignUp> signUp(
            @RequestBody UserReqDTO.SignUp dto
    ) {
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.signUp(dto));
    }

    // 마이페이지 조회
    @GetMapping("/v1/users/{userId}/mypage")
    public ApiResponse<UserResDTO.MyPage> getMyPage(
            @PathVariable Long userId
    ) {
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.getMyPage(userId));
    }
}
