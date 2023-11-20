package com.jejujaju.review.model.dto;

import com.jejujaju.tag.model.dto.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDetailTagResponseDto {
    private Long reviewDetailTagId;
    private Long reviewDetailId;
    private Tag tag;
}
