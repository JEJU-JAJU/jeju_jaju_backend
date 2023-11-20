package com.jejujaju.tag.model.mapper;

import com.jejujaju.tag.model.dto.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {

    @Results(id = "tagMap", value = {
            @Result(property = "tagId", column = "tag_id"),
            @Result(property = "category", column = "category_code",
                    one = @One(select = "com.jejujaju.category.model.mapper.CategoryMapper.selectCategoryByCode")),
            @Result(property = "content", column = "content")
    })
    @Select("SELECT * FROM tag WHERE category_code = #{categoryCode}")
    List<Tag> selectTagByCategoryCode(String categoryCode);

    @ResultMap("tagMap")
    @Select("SELECT * FROM tag WHERE tag_id = #{tagId}")
    Tag selectTagByTagId(Long tagId);
}
