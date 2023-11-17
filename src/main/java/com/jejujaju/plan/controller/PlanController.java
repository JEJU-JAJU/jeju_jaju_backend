package com.jejujaju.plan.controller;

import com.jejujaju.place.model.dto.PlaceRequestDto;
import com.jejujaju.place.model.service.PlaceService;
import com.jejujaju.plan.model.dto.*;
import com.jejujaju.plan.model.service.PlanPlaceService;
import com.jejujaju.plan.model.service.PlanService;
import com.jejujaju.user.model.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<?> savePlan(@AuthenticationPrincipal User user, @RequestBody PlanRequestDto plan){
        PlanSaveDto newPlan = PlanSaveDto.builder()
                .title(plan.getTitle())
                .userId(user.getUserId())
                .description(plan.getDescription())
                .build();

        List<PlaceRequestDto> placeList = plan.getPlaceList();

        planService.savePlan(newPlan, placeList);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{plan-id}")
    public ResponseEntity<?> findPlan(@PathVariable("plan-id") Long planId){
        PlanResponseDto plan = planService.findPlanByPlanId(planId);
        return new ResponseEntity<PlanResponseDto>(plan,HttpStatus.OK);
    }
}
