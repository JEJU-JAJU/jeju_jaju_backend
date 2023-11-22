package com.jejujaju.place.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequestDto {
    private Long placeId;
    private String name;
    private String description;
    private String addr;
    private String addressName;
    private String phone;
    private String url;
    private String img;
    private String x;
    private String y;
    private String categoryCode;
}
