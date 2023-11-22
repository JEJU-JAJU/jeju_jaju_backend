package com.jejujaju.plan.model.mapper;

import com.jejujaju.plan.model.dto.PlanPlaceResponseDto;
import com.jejujaju.plan.model.dto.PlanPlaceSaveDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlanPlaceMapper {

    @InsertProvider(type = PlanPlaceProvider.class, method="insertPlanPlace")
    @Options(useGeneratedKeys = true, keyProperty = "planPlaceId")
    void insertPlanPlace(PlanPlaceSaveDto planPlace);

    @Results({
            @Result(property = "planPlaceId", column = "plan_place_id"),
            @Result(property = "planId", column = "plan_id"),
            @Result(property = "nth", column = "nth"),
            @Result(property = "place", column = "place_id",
                    one = @One(select = "com.jejujaju.place.model.mapper.PlaceMapper.selectPlaceByPlaceId"))
    })
    @SelectProvider(type = PlanPlaceProvider.class, method = "selectPlanPlaceByPlanId")
    List<PlanPlaceResponseDto> selectPlanPlaceByPlanId(Long planId);

    @DeleteProvider(type = PlanPlaceProvider.class, method = "deletePlanPlaceByPlanPlaceId")
    void deletePlanPlaceByPlanPlaceId(Long planPlaceId);

    @DeleteProvider(type = PlanPlaceProvider.class, method = "deletePlanPlaceByPlanId")
    void deletePlanPlaceByPlanId(Long planId);
}
