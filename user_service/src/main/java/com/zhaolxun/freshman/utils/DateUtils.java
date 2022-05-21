package com.zhaolxun.freshman.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author zhao
 * @create 2022-04-24 22:22
 */

public class DateUtils {

    /**
     * 将2019-06-03T16:00:00.000Z日期格式转换为2019-06-03 16:00:00格式
     *
     * @param oldDateStr
     * @return
     */
    public static Date transferDateFormat(String oldDateStr) {
        if (StringUtils.isBlank(oldDateStr)) {
            return null;
        }
        Date date = null;
        Date date1 = null;
        String dateStr = null;
        try {
            dateStr = oldDateStr.replace("Z", " UTC");//是空格+UTC
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            date1 = df.parse(dateStr);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date = df1.parse(date1.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}