package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exceptions.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<UserResDTO.SignUp> signUp(
            @Valid @RequestBody UserReqDTO.SignUp dto
    ) {
        BaseSuccessCode code = UserSuccessCode.OK_SIGNUP;
        return ApiResponse.onSuccess(code, userService.signUp(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<UserResDTO.Login> login(
            @Valid @RequestBody UserReqDTO.Login dto
    ) {
        BaseSuccessCode code = UserSuccessCode.OK_LOGIN;
        return ApiResponse.onSuccess(code, userService.login(dto));
    }

    // 마이페이지 조회
//    @GetMapping("/{userId}/mypage")
//    public ApiResponse<UserResDTO.MyPage> getMyPage(
//            @PathVariable Long userId
//    ) {
//        BaseSuccessCode code = UserSuccessCode.OK;
//        return ApiResponse.onSuccess(code, userService.getMyPage(userId));
//    }

    @GetMapping("/mypage")
    public ApiResponse<UserResDTO.MyPage> getMyPage(
            Authentication authentication
    ) {
        String email = authentication.getName();
        BaseSuccessCode code = UserSuccessCode.OK_MYPAGE;
        return ApiResponse.onSuccess(code, userService.getMyPage(email));
    }
}
