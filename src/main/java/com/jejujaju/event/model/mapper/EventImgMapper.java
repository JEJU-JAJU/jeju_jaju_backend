package com.jejujaju.event.model.mapper;

import com.jejujaju.event.model.dto.EventImg;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventImgMapper {

    @Insert("INSERT INTO event_img (event_id, original_file_name, stored_file_path, file_size) " +
            "VALUES (#{eventId}, #{originalFileName}, #{storedFilePath}, #{fileSize})")
    void insertEventImg(EventImg eventImg);

    @Results({
            @Result(property = "eventImgId", column = "event_img_id"),
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "originalFileName", column = "original_file_name"),
            @Result(property = "storedFilePath", column = "stored_file_path"),
            @Result(property = "fileSize", column = "file_size")
    })
    @Select("SELECT * FROM event_img WHERE event_id = #{eventId}")
    List<EventImg> selectEventByEventId(Long eventId);

    @Delete("DELETE FROM event_img WHERE event_id = #{eventId}")
    void deleteEventImgByEventId(Long eventId);
}
