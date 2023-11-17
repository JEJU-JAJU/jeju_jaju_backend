package com.jejujaju.plan.model.service;

import com.jejujaju.plan.model.dto.PlanPlaceResponseDto;
import com.jejujaju.plan.model.dto.PlanPlaceSaveDto;

import java.util.List;

public interface PlanPlaceService {
    void savePlanPlace(PlanPlaceSaveDto planPlace);
    List<PlanPlaceResponseDto> findPlanPlaceByPlanId(Long planId);
}
