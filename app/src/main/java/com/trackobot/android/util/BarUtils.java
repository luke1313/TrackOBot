package com.trackobot.android.util;

import android.content.Context;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class BarUtils {

  private static final String STATUS_BAR_HEIGHT_IDENTIFIER = "status_bar_height";
  private static final String DIMEN_IDENTIFIER = "dimen";
  private static final String ANDROID_IDENTIFIER = "android";
  private static final String NAVIGATION_BAR_HEIGHT_IDENTIFIER = "navigation_bar_height";

  public static int getStatusBarHeight(Context context) {
    int result = 0;
    int resourceId = context.getResources()
        .getIdentifier(STATUS_BAR_HEIGHT_IDENTIFIER, DIMEN_IDENTIFIER, ANDROID_IDENTIFIER);
    if (resourceId > 0) {
      result = context.getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }

  public static int getNavigationBarHeight(Context context) {
    int result = 0;
    int resourceId = context.getResources()
        .getIdentifier(NAVIGATION_BAR_HEIGHT_IDENTIFIER, DIMEN_IDENTIFIER, ANDROID_IDENTIFIER);
    if (resourceId > 0) {
      result = context.getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }
}
