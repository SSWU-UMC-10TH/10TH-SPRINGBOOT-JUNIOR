package com.example.umc10th.domain.store.dto;

import jakarta.validation.constraints.NotNull;

public class StoreReqDTO {

    // 식당 정보 + 주소 조회
    public record GetStoreInfo(
            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId

//            @NotNull(message = "주소 ID는 필수입니다.")
//            Long addressId
    ) {
    }
}
