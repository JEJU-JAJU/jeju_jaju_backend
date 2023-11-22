package com.jejujaju.plan.model.dto;

import com.jejujaju.place.model.dto.PlaceResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanPlaceResponseDto {
    private Long planPlaceId;
    private Long planId;
    private Long nth;
    private PlaceResponseDto place;
}
