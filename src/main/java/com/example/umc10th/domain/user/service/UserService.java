package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;

}
