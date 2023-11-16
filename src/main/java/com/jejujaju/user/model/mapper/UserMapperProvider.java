package com.jejujaju.user.model.mapper;

import com.jejujaju.user.model.dto.User;
import org.apache.ibatis.jdbc.SQL;

public class UserMapperProvider {

    public String insertUser(){
        return new SQL(){
            {
                INSERT_INTO("user");
                INTO_COLUMNS("login_id", "name", "password", "email", "phone");
                INTO_VALUES("#{loginId}", "#{name}", "#{password}", "#{email}", "#{phone}");
            }
        }.toString();
    }

    public String selectUserByUserId(){
        return new SQL(){
            {
                SELECT("*");
                FROM("user");
                WHERE("user_id = #{userId}");
            }
        }.toString();
    }

    public String selectUserByLoginId(){
        return new SQL(){
            {
                SELECT("*");
                FROM("user");
                WHERE("login_id = #{loginId}");
            }
        }.toString();
    }

    public String updateUser(final User user){
        return new SQL(){
            {
                UPDATE("user");
                if(user.getName() != null){
                    SET("name = #{name}");
                }
                if(user.getEmail() != null){
                    SET("email = #{email}");
                }
                if(user.getPhone() != null){
                    SET("phone = #{phone}");
                }
                WHERE("user_id = #{userId}");
            }
        }.toString();
    }

    public String updateUserPwd(){
        return new SQL(){
            {
                UPDATE("user");
                SET("password = #{password}");
                WHERE("user_id = #{userId}");
            }
        }.toString();
    }

    public String deleteUser(){
        return new SQL(){
            {
                DELETE_FROM("user");
                WHERE("user_id = #{userId}");
            }
        }.toString();
    }

}
