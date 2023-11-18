package com.jejujaju.plan.model.service;

import com.jejujaju.place.model.dto.PlaceRequestDto;
import com.jejujaju.place.model.mapper.PlaceMapper;
import com.jejujaju.plan.model.dto.PlanPlaceResponseDto;
import com.jejujaju.plan.model.dto.PlanPlaceSaveDto;
import com.jejujaju.plan.model.dto.PlanResponseDto;
import com.jejujaju.plan.model.dto.PlanSaveDto;
import com.jejujaju.plan.model.mapper.PlanMapper;
import com.jejujaju.plan.model.mapper.PlanPlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanServiceImpl implements PlanService{

    private final PlanMapper planMapper;
    private final PlaceMapper placeMapper;
    private final PlanPlaceMapper planPlaceMapper;

    @Override
    public void savePlan(PlanSaveDto plan, List<PlaceRequestDto> placeList){
        planMapper.insertPlan(plan);
        savePlaceWithPlanPlace(plan.getPlanId(), placeList);
    }

    @Override
    public PlanResponseDto findPlanByPlanId(Long planId){
        planMapper.updateHit(planId);
        return planMapper.selectPlanByPlanId(planId);
    }

    @Override
    public void updatePlan(PlanSaveDto plan, List<PlaceRequestDto> placeList) {
        planMapper.updatePlan(plan);
        deletePlaceWithPlanPlace(plan.getPlanId());
        savePlaceWithPlanPlace(plan.getPlanId(), placeList);
    }

    @Override
    public void deletePlan(Long planId) {
        deletePlaceWithPlanPlace(planId);
        planMapper.deletePlan(planId);
    }

    public void savePlaceWithPlanPlace(Long planId, List<PlaceRequestDto> placeList){
        int nth = 0;
        for(PlaceRequestDto newPlace : placeList){
            placeMapper.insertPlace(newPlace);

            planPlaceMapper.insertPlanPlace(PlanPlaceSaveDto.builder()
                    .planId(planId)
                    .nth((long)++nth)
                    .placeId(newPlace.getPlaceId())
                    .build()
            );
        }
    }

    public void deletePlaceWithPlanPlace(Long planId){
        List<PlanPlaceResponseDto> planPlaceList = planPlaceMapper.selectPlanPlaceByPlanId(planId);
        for(PlanPlaceResponseDto planPlace : planPlaceList){
            placeMapper.deletePlace(planPlace.getPlace().getPlaceId());
        }
        planPlaceMapper.deletePlanPlaceByPlanId(planId);
    }
}
