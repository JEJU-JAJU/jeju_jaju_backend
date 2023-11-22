package com.jejujaju.event.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventImg {
    private Long eventImgId;
    private Long eventId;
    private String originalFileName;
    private String storedFilePath;
    private Long fileSize;
}
