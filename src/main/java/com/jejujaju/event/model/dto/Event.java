package com.jejujaju.event.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private Long eventId;
    private Long userId;
    private Long planId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String description;
    private String badgeImg;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
