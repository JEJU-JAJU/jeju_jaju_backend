package com.jejujaju.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicDto {
    private Long userId;
    private String loginId;
    private String name;
    private String email;
    private String phone;
}
