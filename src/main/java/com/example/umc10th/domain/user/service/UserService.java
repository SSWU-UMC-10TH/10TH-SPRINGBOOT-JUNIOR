package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.security.entity.AuthUser;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입
    @Transactional
    public UserResDTO.SignUp signUp(UserReqDTO.SignUp request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new UserException(UserErrorCode.USER_EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByNickname(request.nickname())) {
            throw new UserException(UserErrorCode.USER_NICKNAME_ALREADY_EXISTS);
        }

        User user = User.builder()
                .name(request.name())
                .gender(request.gender())
                .birthDate(request.birthDate())
                .nickname(request.nickname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .profileUrl(request.profileUrl())
                .point(0L)
                .build();

        userRepository.save(user);

        return UserConverter.toSignUp(user);
    }

    // 로그인
    public UserResDTO.Login login(UserReqDTO.Login request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UserException(UserErrorCode.USER_PASSWORD_NOT_MATCH);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthUser(user));

        return UserConverter.toLogin(accessToken);
    }

    public UserResDTO.GetMyPage getMyPage(AuthUser authUser) {
        return UserConverter.toGetMyPage(authUser.getUser());
    }

    // 마이페이지 조회
    public UserResDTO.GetMyPage getMyPage(UserReqDTO.GetMyPage request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toGetMyPage(user);
    }
}