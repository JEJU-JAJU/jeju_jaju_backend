package com.jejujaju.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatter {

    public Timestamp StringToTimestamp(String date){
        return Timestamp.valueOf(LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
