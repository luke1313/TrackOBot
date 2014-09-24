package com.trackobot.android.util;

/**
 * Created by lukasolsen on 24/09/14.
 */
public class StringUtil {

  public static String capitalize(String string) {
    StringBuilder sb = new StringBuilder(string);
    sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
    return sb.toString();
  }

}
