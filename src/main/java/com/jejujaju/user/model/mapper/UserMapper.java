package com.jejujaju.user.model.mapper;

import com.jejujaju.user.model.dto.User;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;

@Mapper
public interface UserMapper {

    @InsertProvider(type = UserMapperProvider.class, method = "insertUser")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "userId", before = false, resultType = Long.class)
    void insertUser(User user) throws SQLException;

    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "loginId", column = "login_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    @SelectProvider(type = UserMapperProvider.class, method = "selectUserByUserId")
    User selectUserByUserId(Long userId);

    @ResultMap("userMap")
    @SelectProvider(type = UserMapperProvider.class, method = "selectUserByLoginId")
    User selectUserByLoginId(String loginId);

    @UpdateProvider(type = UserMapperProvider.class, method = "updateUser")
    User updateUser(User user);

    @DeleteProvider(type = UserMapperProvider.class, method = "deleteUser")
    int deleteUser(Long userId);
}
