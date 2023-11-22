package com.jejujaju.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSaveDto {
    private Long reviewId;
    private Long planId;
    private Long userId;
    private String description;
}
