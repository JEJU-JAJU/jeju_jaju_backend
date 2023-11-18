package com.jejujaju.tag.model.service;

import com.jejujaju.tag.model.dto.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findTagByCategoryCode(String categoryCode);
}
