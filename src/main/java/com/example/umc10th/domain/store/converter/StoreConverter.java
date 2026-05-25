package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Address;
import com.example.umc10th.domain.store.entity.Store;

public class StoreConverter {

    public static StoreResDTO.GetStoreInfo toGetStoreInfo(Store store) {
        return new StoreResDTO.GetStoreInfo(
                store.getStoreId(),
                store.getStoreName(),
                store.getAddress().getZipcode(),
                store.getAddress().getSido(),
                store.getAddress().getSigungu(),
                store.getAddress().getEmd()
        );
    }
}
