package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.FoodCategory;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.UserFoodPreference;
import com.example.umc10th.domain.user.exceptions.UserException;
import com.example.umc10th.domain.user.exceptions.code.FoodCategoryErrorCode;
import com.example.umc10th.domain.user.exceptions.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.FoodCategoryRepository;
import com.example.umc10th.domain.user.repository.UserFoodPreferenceRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.security.util.JwtUtil;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserFoodPreferenceRepository userFoodPreferenceRepository;

    @Transactional
    public UserResDTO.SignUp signUp(UserReqDTO.SignUp dto) {

        // 이미 회원가입한 이메일일 때
        if(userRepository.existsByEmail(dto.email())) {
            throw new UserException(UserErrorCode.DUPLICATE_EMAIL);
        }

        // 이미 회원가입된 닉네임일 때
        if(userRepository.existsByNickname(dto.nickname())) {
            throw new UserException(UserErrorCode.DUPLICATE_NICKNAME);
        }

        // 비밀번호 암호화 및 User 엔티티 생성 (엔티티 정적 팩터리 메서드 활용)
        String encodedPassword = passwordEncoder.encode(dto.password());
        User user = User.toUser(dto, encodedPassword);
        User savedUser = userRepository.save(user);

        // findByIds 5번 말고 find'All'ById 사용해서 쿼리 1번!
        List<Long> foodCategoryIds = dto.preferenceFoodIds().stream()
                .map(Integer::longValue)
                        .toList();

        List<FoodCategory> foodCategories = foodCategoryRepository.findAllById(foodCategoryIds);

        // 요청 Id 개수와 DB에서 가져온 Id 개수 검증
        if (foodCategories.size() != foodCategoryIds.size()) {
            throw new UserException(FoodCategoryErrorCode.FOOD_CATEGORY_NOT_FOUND);
        }

        // User 리스트에 넣지 말고 중간 테이블에 저장
        List<UserFoodPreference> preferences = foodCategories.stream()
                .map(foodCategory -> UserFoodPreference.builder()
                        .user(savedUser)
                        .foodCategory(foodCategory)
                        .build())
                .toList();

        // 생성된 선호 음식 매핑 데이터를 DB에 명시적으로 저장
        userFoodPreferenceRepository.saveAll(preferences);

        // 결과 반환
        return UserConverter.toSignUpResult(savedUser);
    }

    public UserResDTO.Login login(UserReqDTO.Login dto) {
        User user = userRepository.findByEmail(dto.email())
            .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        // 패스워드 일치 확인
        if(!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVAlID_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(user);

        return UserConverter.toLoginResult(accessToken);
    }

    public UserResDTO.MyPage getMyPage(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toMyPage(user);
    }
}
