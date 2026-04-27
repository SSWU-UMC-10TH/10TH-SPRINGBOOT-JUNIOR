package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;

public class StoreConverter {

    public static StoreResDTO.GetStoreInfo toGetStoreInfo(Store store) {
        return StoreResDTO.GetStoreInfo.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .build();
    }
}
