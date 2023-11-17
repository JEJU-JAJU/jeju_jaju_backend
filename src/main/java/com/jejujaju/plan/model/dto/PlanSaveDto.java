package com.jejujaju.plan.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanSaveDto {
    private Long planId;
    private String title;
    private Long userId;
    private String description;
}
