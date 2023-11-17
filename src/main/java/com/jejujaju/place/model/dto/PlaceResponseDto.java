package com.jejujaju.place.model.dto;

import com.jejujaju.category.model.dto.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResponseDto {
    private Long placeId;
    private String name;
    private String description;
    private String addr;
    private String phone;
    private String url;
    private String img;
    private String x;
    private String y;
    private Category category;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
