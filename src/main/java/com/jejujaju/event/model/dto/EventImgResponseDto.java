package com.jejujaju.event.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventImgResponseDto {
    private String originalFileName;
    private byte[] imageData;
}
