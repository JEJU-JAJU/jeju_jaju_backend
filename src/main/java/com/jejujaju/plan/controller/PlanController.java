package com.jejujaju.plan.controller;

import com.jejujaju.place.model.dto.PlaceRequestDto;
import com.jejujaju.plan.model.dto.PlanRequestDto;
import com.jejujaju.plan.model.dto.PlanResponseDto;
import com.jejujaju.plan.model.dto.PlanSaveDto;
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
    public ResponseEntity<Void> savePlan(@AuthenticationPrincipal User user, @RequestBody PlanRequestDto plan){
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
    public ResponseEntity<PlanResponseDto> findPlan(@PathVariable("plan-id") Long planId){
        PlanResponseDto plan = planService.findPlanByPlanId(planId);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<List<PlanResponseDto>> findMyPlan(@AuthenticationPrincipal User user){
        List<PlanResponseDto> planList = planService.findPlanByUserId(user.getUserId());
        return new ResponseEntity<>(planList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> findAllPlan(){
        List<PlanResponseDto> planList = planService.findAllPlan();
        return new ResponseEntity<>(planList, HttpStatus.OK);
    }

    @PutMapping("/{plan-id}")
    public ResponseEntity<Void> updatePlan(@PathVariable("plan-id") Long planId, @RequestBody PlanRequestDto plan){
        PlanSaveDto newPlan = PlanSaveDto.builder()
                .planId(planId)
                .title(plan.getTitle())
                .description(plan.getDescription())
                .build();

        List<PlaceRequestDto> placeList = plan.getPlaceList();

        planService.updatePlan(newPlan, placeList);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{plan-id}")
    public ResponseEntity<Void> deletePlan(@PathVariable("plan-id") Long planId){
        planService.deletePlan(planId);
        return ResponseEntity.ok().build();
    }
}
