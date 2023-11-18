package com.jejujaju.plan.model.mapper;

import com.jejujaju.plan.model.dto.PlanResponseDto;
import com.jejujaju.plan.model.dto.PlanSaveDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlanMapper {

    @InsertProvider(type = PlanProvider.class, method="insertPlan")
    @Options(useGeneratedKeys = true, keyProperty = "planId")
    void insertPlan(PlanSaveDto plan);

    @Results(id = "planMap", value = {
            @Result(property = "planId", column = "plan_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.jejujaju.user.model.mapper.UserMapper.selectUserByUserId")),
            @Result(property = "description", column = "description"),
            @Result(property = "hit", column = "hit"),
            @Result(property = "placeList", column = "plan_id",
                    many = @Many(select = "com.jejujaju.plan.model.mapper.PlanPlaceMapper.selectPlanPlaceByPlanId")),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    @SelectProvider(type = PlanProvider.class, method = "selectPlanByPlanId")
    PlanResponseDto selectPlanByPlanId(Long planId);

    @ResultMap("planMap")
    @SelectProvider(type = PlanProvider.class, method = "selectAllPlan")
    List<PlanResponseDto> selectAllPlan();

    @UpdateProvider(type = PlanProvider.class, method = "updatePlan")
    void updatePlan(PlanSaveDto plan);

    @DeleteProvider(type = PlanProvider.class, method = "deletePlan")
    void deletePlan(Long planId);

    @UpdateProvider(type = PlanProvider.class, method = "updateHit")
    void updateHit(Long planId);
}
