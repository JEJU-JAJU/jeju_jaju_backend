package com.jejujaju.place.model.mapper;

import com.jejujaju.place.model.dto.PlaceRequestDto;
import com.jejujaju.place.model.dto.PlaceResponseDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PlaceMapper {

    @InsertProvider(type = PlaceProvider.class, method = "insertPlace")
    @Options(useGeneratedKeys = true, keyProperty = "placeId")
    void insertPlace(PlaceRequestDto place);

    @Results({
            @Result(property = "placeId", column = "place_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "addr", column = "addr"),
            @Result(property = "addressName", column = "address_name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "url", column = "url"),
            @Result(property = "img", column = "img"),
            @Result(property = "x", column = "x"),
            @Result(property = "y", column = "y"),
            @Result(property = "category", column = "category_code",
                    one = @One(select = "com.jejujaju.category.model.mapper.CategoryMapper.selectCategoryByCode")),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    @SelectProvider(type = PlaceProvider.class, method = "selectPlaceByPlaceId")
    PlaceResponseDto selectPlaceByPlaceId(Long placeId);

    @UpdateProvider(type = PlaceProvider.class, method = "selectPlaceByPlaceId")
    void updatePlace(PlaceRequestDto place);

    @DeleteProvider(type = PlaceProvider.class, method = "deletePlace")
    void deletePlace(Long placeId);
}
