package com.jejujaju.tag.model.service;

import com.jejujaju.tag.model.dto.Tag;
import com.jejujaju.tag.model.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagMapper tagMapper;

    @Override
    public List<Tag> findTagByCategoryCode(String categoryCode) {
        return tagMapper.selectTagByCategoryCode(categoryCode);
    }
}
