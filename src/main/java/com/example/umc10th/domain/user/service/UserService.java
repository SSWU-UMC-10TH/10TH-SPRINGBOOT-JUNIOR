package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResDTO.SignUp signUp(UserReqDTO.SignUp dto) {
        throw new UnsupportedOperationException("아직 구현되지 않은 기능입니다.");
    }
}
