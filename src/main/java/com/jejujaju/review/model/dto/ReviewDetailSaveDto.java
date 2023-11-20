package com.jejujaju.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDetailSaveDto {
    private Long reviewDetailId;
    private Long reviewId;
    private Long nth;
    private Long placeId;
}
