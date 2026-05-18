package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.FoodCategory;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.UserFoodPreference;
import com.example.umc10th.domain.user.exceptions.UserException;
import com.example.umc10th.domain.user.exceptions.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.FoodCategoryRepository;
import com.example.umc10th.domain.user.repository.UserFoodPreferenceRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserFoodPreferenceRepository userFoodPreferenceRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResDTO.SignUp signUp(UserReqDTO.SignUp dto) {

        // 이미 회원가입한 이메일일 때
        if(userRepository.existsByEmail(dto.email())) {
            throw new UserException(UserErrorCode.DUPLICATE_EMAIL);
        }

        // 이미 회원가입된 닉네임일 때
        if(userRepository.existsByNickname(dto.nickname())) {
            throw new UserException(UserErrorCode.DUPLICATE_NICKNAME);
        }

        User user = User.builder()
                .name(dto.name())
                .nickname(dto.nickname())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .birth(dto.birth())
                .gender(dto.gender())
                .addressLine1(dto.addressLine1())
                .addressLine2(dto.addressLine2())
                .point(0L) // 기본 값만 설정해두기
                .build();

        User savedUser = userRepository.save(user);

        List<UserFoodPreference> preferences = dto.preferenceFoodIds().stream()
                .map(foodCategoryId -> {
                    FoodCategory foodCategory = foodCategoryRepository.findById(foodCategoryId.longValue())
                            .orElseThrow(() -> new UserException(UserErrorCode.FOOD_CATEGORY_NOT_FOUND));

                    return UserFoodPreference.builder()
                            .user(savedUser)
                            .foodCategory(foodCategory)
                            .build();
                })
                .toList();

        return UserResDTO.SignUp.builder()
                .name(savedUser.getName())
                .nickname(savedUser.getNickname())
                .email(savedUser.getEmail())
                .build();


    }

    public UserResDTO.MyPage getMyPage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toMyPage(user);
    }
}
