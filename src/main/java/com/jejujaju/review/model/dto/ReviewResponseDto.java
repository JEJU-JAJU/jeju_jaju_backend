package com.jejujaju.review.model.dto;

import com.jejujaju.user.model.dto.UserBasicDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;
    private Long planId;
    private UserBasicDto user;
    private String description;
    private List<ReviewDetailResponseDto> reviewDetailList;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
