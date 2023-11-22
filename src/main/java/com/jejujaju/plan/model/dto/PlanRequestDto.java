package com.jejujaju.plan.model.dto;

import com.jejujaju.place.model.dto.PlaceRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanRequestDto {
    private String title;
    private String description;
    private List<PlaceRequestDto> placeList;
}
