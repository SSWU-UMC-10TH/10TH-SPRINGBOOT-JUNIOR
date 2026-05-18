package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.SignUp> signUp(
            @Valid @RequestBody UserReqDTO.SignUp request
    ) {
        return ApiResponse.onSuccess(
                UserSuccessCode.SIGN_UP_SUCCESS,
                userService.signUp(request)
        );
    }

    @PostMapping("/me")
    public ApiResponse<UserResDTO.GetMyPage> getMyPage(
            @Valid @RequestBody UserReqDTO.GetMyPage request
    ) {
        return ApiResponse.onSuccess(
                UserSuccessCode.GET_MY_PAGE_SUCCESS,
                userService.getMyPage(request)
        );
    }
}