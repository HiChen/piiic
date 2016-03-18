package com.gewara.piiic.utility;

/**
 * Created by user on 2016/1/26.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gewara.piiic.Configs;
import com.gewara.piiic.configs.L;

public final class DateUtil {
    private static final DateFormat MILLIS_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private static final DateFormat SECOND_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private static final DateFormat SIMPLE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private static final String TAG = Configs.makeTag("DateUtil");

    public static String getSimpleString(Date paramDate) {
        return SIMPLE_FORMAT.format(paramDate);
    }

    public static Date getUTCDate(String paramString) {
        try {
            Date localDate2 = MILLIS_FORMAT.parse(paramString);
            return localDate2;
        } catch (ParseException localParseException1) {
            L.e(TAG, localParseException1.getMessage(), new Object[]{localParseException1});
            try {
                Date localDate1 = SECOND_FORMAT.parse(paramString);
                return localDate1;
            } catch (ParseException localParseException2) {
                L.e(TAG, localParseException2.getMessage(), new Object[]{localParseException2});
            }
        }
        return null;
    }

    public static String getUTCString(Date paramDate) {
        if (paramDate == null)
            return "";
        try {
            String str = MILLIS_FORMAT.format(paramDate);
            return str;
        } catch (Exception localException) {

            L.e(TAG, localException.getMessage(), new Object[]{localException});
        }
        return SECOND_FORMAT.format(paramDate);
    }
}
