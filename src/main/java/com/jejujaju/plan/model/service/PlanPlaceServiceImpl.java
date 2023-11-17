package com.jejujaju.plan.model.service;

import com.jejujaju.plan.model.dto.PlanPlaceResponseDto;
import com.jejujaju.plan.model.dto.PlanPlaceSaveDto;
import com.jejujaju.plan.model.mapper.PlanPlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanPlaceServiceImpl implements PlanPlaceService{

    private final PlanPlaceMapper planPlaceMapper;

    @Override
    public void savePlanPlace(PlanPlaceSaveDto planPlace) {
        planPlaceMapper.insertPlanPlace(planPlace);
    }

    @Override
    public List<PlanPlaceResponseDto> findPlanPlaceByPlanId(Long planId) {
        return planPlaceMapper.selectPlanPlaceByPlanId(planId);
    }
}
