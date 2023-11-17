package com.jejujaju.plan.model.dto;

import com.jejujaju.place.model.dto.PlaceResponseDto;
import com.jejujaju.user.model.dto.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanResponseDto {
    private Long planId;
    private String title;
    private User user;
    private String description;
    private Long hit;
    private List<PlaceResponseDto> placeList;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
