package com.jejujaju.plan.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanPlaceSaveDto {
    private Long planPlaceId;
    private Long planId;
    private Long nth;
    private Long placeId;
}
