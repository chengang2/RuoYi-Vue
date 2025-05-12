package com.ruoyi.suyuan.tools;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateFormatUtil {
    public static String format(Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
