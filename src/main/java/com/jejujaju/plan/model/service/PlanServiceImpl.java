package com.jejujaju.plan.model.service;

import com.jejujaju.place.model.dto.PlaceRequestDto;
import com.jejujaju.place.model.mapper.PlaceMapper;
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

        int nth = 0;
        for(PlaceRequestDto newPlace : placeList){
            placeMapper.insertPlace(newPlace);

            planPlaceMapper.insertPlanPlace(PlanPlaceSaveDto.builder()
                    .planId(plan.getPlanId())
                    .nth((long)++nth)
                    .placeId(newPlace.getPlaceId())
                    .build()
            );
        }
    }

    @Override
    public PlanResponseDto findPlanByPlanId(Long planId){
        return planMapper.selectPlanByPlanId(planId);
    }
}
