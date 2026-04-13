package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResDTO.CreateReview createReview(
            Long userMissionId,
            ReviewReqDTO.CreateReview dto,
            List<MultipartFile> images)
    {
        throw new UnsupportedOperationException("아직 구현되지 않은 기능입니다.");
    }
}
