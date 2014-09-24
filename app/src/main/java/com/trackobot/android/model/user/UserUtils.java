package com.trackobot.android.model.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class UserUtils {

  private static final String PREFS_NAME = "TrackOBotPrefs";
  private static final String KEY_USERNAME = "userName";
  private static final String KEY_ACCES_TOKEN = "accesToken";

  public static boolean isUserLoggedin(Context context) {
    String userName = getUserName(context);
    String accesToken = getAccesToken(context);
    if (userName.isEmpty() || accesToken.isEmpty()) {
      return false;
    }
    return true;
  }

  public static String getUserName(Context context) {
    SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
    String userName = settings.getString(KEY_USERNAME, "");
    return userName;
  }

  public static String getAccesToken(Context context) {
    SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
    String accesToken = settings.getString(KEY_ACCES_TOKEN, "");
    return accesToken;
  }

  public static void logUserIn(Context context, String user, String accesToken) {
    SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
    SharedPreferences.Editor editor = settings.edit();
    editor.putString(KEY_USERNAME, user);
    editor.putString(KEY_ACCES_TOKEN, accesToken);
    editor.commit();
  }
}
