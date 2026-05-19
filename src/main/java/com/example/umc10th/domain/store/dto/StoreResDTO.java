package com.example.umc10th.domain.store.dto;

import com.example.umc10th.domain.store.enums.Emd;
import com.example.umc10th.domain.store.enums.Sido;
import com.example.umc10th.domain.store.enums.Sigungu;
import lombok.Builder;

import java.time.LocalDateTime;

public class StoreResDTO {

    @Builder
    public record GetStoreInfo(
            Long storeId,
            String storeName,
            String zipcode,
            Sido sido,
            Sigungu sigungu,
            Emd emd
    ) {
    }
}
