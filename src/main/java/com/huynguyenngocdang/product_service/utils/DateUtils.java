package com.huynguyenngocdang.product_service.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtils {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final ZoneId VIETNAM_TIME_ZONE = ZoneId.of("Asia/Ho_Chi_Minh");

    public static String getCurrentDatetime(){
        LocalDateTime now = LocalDateTime.now(VIETNAM_TIME_ZONE);
        return now.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }
}
