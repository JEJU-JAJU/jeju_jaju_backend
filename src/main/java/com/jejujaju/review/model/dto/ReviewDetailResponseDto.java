package com.jejujaju.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDetailResponseDto {
    private Long reviewDetailId;
    private Long nth;
    private Long placeId;
    private List<ReviewDetailTagResponseDto> tagList;
}
