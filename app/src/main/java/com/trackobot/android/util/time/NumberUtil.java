package com.trackobot.android.util.time;

import java.math.BigDecimal;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class NumberUtil {

  public static float round(float d, int decimalPlace) {
    BigDecimal bd = new BigDecimal(Float.toString(d));
    bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
    return bd.floatValue();
  }
}
