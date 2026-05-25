package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.StoreReqDTO;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    // 식당 정보 조회
    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.GetStoreInfo> getStoreInfo(
            @PathVariable Long storeId
    ) {
        StoreReqDTO.GetStoreInfo request = new StoreReqDTO.GetStoreInfo(storeId);

        return ApiResponse.onSuccess(
                StoreSuccessCode.GET_STORE_SUCCESS,
                storeService.getStoreInfo(request)
        );
    }
}