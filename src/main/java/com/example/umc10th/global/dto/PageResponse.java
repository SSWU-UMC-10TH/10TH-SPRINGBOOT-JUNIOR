package com.example.umc10th.global.dto;

import java.util.List;

public record PageResponse<T>(
        List<T> data,
        Integer pageNumber,
        Integer pageSize
) {}
