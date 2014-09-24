package com.trackobot.android.util.time;

import android.util.Log;
import java.math.BigDecimal;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class TimeUtils {

  private static final int SECONDS_OF_MINUTE = 60;

  public static String convertSecondsToString(long seconds) {
    float minutes = (float) seconds / (float) SECONDS_OF_MINUTE;
    minutes = NumberUtil.round(minutes, 1);
    return minutes + " Minutes";
  }

  public static DateTime createDateFromString(String timeString) {
    if (timeString.length() > 19) {
      timeString = timeString.substring(0, 19);
    }
    timeString = timeString.replace("T", " ");
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    DateTime dt = formatter.parseDateTime(timeString);
    return dt;
  }
}
