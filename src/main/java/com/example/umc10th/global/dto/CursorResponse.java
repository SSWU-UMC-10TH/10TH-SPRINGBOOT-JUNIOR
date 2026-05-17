package com.example.umc10th.global.dto;

import java.util.List;

public record CursorResponse<T>(
        List<T> data,
        Long cursor,
        Boolean hasNext
) {}