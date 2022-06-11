package com.dev.cry.Utils;

import java.time.LocalDateTime;
import java.util.Date;

public class DateUtils {

    public static Date LocalDateTimeToDate(LocalDateTime localDateTime){
        return java.sql.Timestamp.valueOf(localDateTime);
    }
}
