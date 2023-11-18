package com.jejujaju.plan.model.service;

import com.jejujaju.place.model.dto.PlaceRequestDto;
import com.jejujaju.plan.model.dto.PlanResponseDto;
import com.jejujaju.plan.model.dto.PlanSaveDto;

import java.util.List;

public interface PlanService {
    void savePlan(PlanSaveDto plan, List<PlaceRequestDto> placeList);
    PlanResponseDto findPlanByPlanId(Long planId);
    void updatePlan(PlanSaveDto plan, List<PlaceRequestDto> placeList);
    void deletePlan(Long planId);
}
