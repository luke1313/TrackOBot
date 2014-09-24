package com.trackobot.android.http;

import android.content.Context;
import android.util.Log;
import com.trackobot.android.Config;
import com.trackobot.android.model.user.UserUtils;

/**
 * Created by lukasolsen on 21/09/14.
 */
public class RequestBuilder {

  public static final String[] TIME_MODES =
      { null, "last_24_hours", "last_3_days", "current_month" };
  public static final String[] MODE_MODES = { null, "ranked", "casual", "arena", "friendly" };

  private static final String DEBUG_TAG = "RequestBuilder";

  private static final String BASE_URL = "https://trackobot.com";

  private static final String SLASH = "/";
  private static final String JSON_SUFFIX = ".json";
  private static final String PARAMETERS_PREFIX = "?";
  private static final String PARAMETER_EQUALS_SIGN = "=";
  private static final String PARAMETER_MORE = "&";

  private static final String PARAMETER_USERNAME = "username";
  private static final String PARAMETER_ACCESTOKEN = "token";

  public static String buildRestUrl(Context context, String apiCall) {
    String url = BASE_URL + SLASH + apiCall + JSON_SUFFIX;
    url = addStandardParameters(context, url);
    logUrl(url);
    return url;
  }

  public static String buildRestUrl(Context context, String apiCategory, String apiCall) {
    String url = BASE_URL + SLASH + apiCategory + SLASH + apiCall + JSON_SUFFIX;
    url = addStandardParameters(context, url);
    logUrl(url);
    return url;
  }

  public static String buildRestUrl(Context context, String apiCategory, String subApiCategory,
      String apiCall) {
    String url =
        BASE_URL + SLASH + apiCategory + SLASH + subApiCategory + SLASH + apiCall + JSON_SUFFIX;
    url = addStandardParameters(context, url);
    logUrl(url);
    return url;
  }

  public static String buildRestUrl(String apiCategory, String apiCall, String userName,
      String accesToken) {
    String url = BASE_URL + SLASH + apiCategory + SLASH + apiCall + JSON_SUFFIX;
    url = addStandardParameters(url, userName, accesToken);
    logUrl(url);
    return url;
  }

  private static void logUrl(String url) {
    if (Config.HTTP_LOGGING) {
      Log.d(DEBUG_TAG, "URL builded: " + url);
    }
  }

  private static String getUsername(Context context) {
    return UserUtils.getUserName(context);
  }

  private static String getAccesToken(Context context) {
    return UserUtils.getAccesToken(context);
  }

  private static String addStandardParameters(Context context, String url) {
    url += PARAMETERS_PREFIX;
    url += buildParameterString(PARAMETER_ACCESTOKEN, getAccesToken(context));
    url += PARAMETER_MORE;
    url += buildParameterString(PARAMETER_USERNAME, getUsername(context));
    return url;
  }

  private static String addStandardParameters(String url, String userName, String accesToken) {
    url += PARAMETERS_PREFIX;
    url += buildParameterString(PARAMETER_ACCESTOKEN, accesToken);
    url += PARAMETER_MORE;
    url += buildParameterString(PARAMETER_USERNAME, userName);
    return url;
  }

  private static String buildParameterString(String key, String value) {
    return key + PARAMETER_EQUALS_SIGN + value;
  }
}
