package com.example.umc10th.domain.store.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class StoreResDTO {

    @Builder
    public record GetStoreInfo(
            Long storeId,
            String storeName,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }
}
