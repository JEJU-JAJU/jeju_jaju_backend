package com.jejujaju.tag.model.dto;

import com.jejujaju.category.model.dto.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long tagId;
    private Category category;
    private String content;
}
