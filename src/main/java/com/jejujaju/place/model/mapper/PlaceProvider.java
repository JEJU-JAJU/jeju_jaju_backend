package com.jejujaju.place.model.mapper;

import org.apache.ibatis.jdbc.SQL;

public class PlaceProvider {

    public String insertPlace(){
        return new SQL(){
            {
                INSERT_INTO("place");
                INTO_COLUMNS("name", "description", "addr", "address_name", "phone", "url", "img", "x", "y", "category_code");
                INTO_VALUES("#{name}", "#{description}", "#{addr}", "#{addressName}", "#{phone}", "#{url}", "#{img}",
                        "#{x}", "#{y}", "#{categoryCode}");
            }
        }.toString();
    }

    public String selectPlaceByPlaceId(){
        return new SQL(){
            {
                SELECT("*");
                FROM("place");
                WHERE("place_id = #{placeId}");
            }
        }.toString();
    }

    public String updatePlace(){
        return new SQL(){
            {
                UPDATE("place");
                SET("description = #{description}");
                WHERE("place_id = #{place_id}");
            }
        }.toString();
    }

    public String deletePlace(){
        return new SQL(){
            {
                DELETE_FROM("place");
                WHERE("place_id = #{placeId}");
            }
        }.toString();
    }
}
