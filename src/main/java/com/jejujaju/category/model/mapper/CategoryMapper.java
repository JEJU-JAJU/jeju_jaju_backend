package com.jejujaju.category.model.mapper;

import com.jejujaju.category.model.dto.Category;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CategoryMapper {

    @Results(id = "categoryMap", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "code", column = "code"),
            @Result(property = "name", column = "name")
    })
    @Select("SELECT * FROM category WHERE code=#{code}")
    Category selectCategoryByCode(String code);
}
