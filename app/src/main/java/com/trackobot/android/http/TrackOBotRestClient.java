package com.trackobot.android.http;

import android.content.Context;
import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.trackobot.android.util.base.BasePresenter;

/**
 * Created by lukasolsen on 21/09/14.
 */
public class TrackOBotRestClient {

  private static final String API_CATEGORY_PROFILE = "profile";
  private static final String API_HISTORY = "history";
  private static final String API_ARENA = "arena";

  private static final String API_SUBCATEGORY_STATS = "stats";
  private static final String API_STATS_CLASSES = "classes";
  private static final String API_STATS_ARENA = "arena";

  private static AsyncHttpClient client = new AsyncHttpClient();

  public static void getHistory(Context context, BasePresenter.ResponseHandler handler) {
    String url = RequestBuilder.buildRestUrl(context, API_CATEGORY_PROFILE, API_HISTORY);
    client.get(url, null, handler);
  }

  public static void getHistoryOfArena(Context context, String arenaId,
      BasePresenter.ResponseHandler handler) {
    String url = RequestBuilder.buildRestUrl(context, API_CATEGORY_PROFILE);
    url += "&arena_id=" + arenaId;
    client.get(url, null, handler);
  }

  public static void getArena(Context context, BasePresenter.ResponseHandler handler) {
    String url = RequestBuilder.buildRestUrl(context, API_CATEGORY_PROFILE, API_ARENA);
    client.get(url, null, handler);
  }

  public static void getStats(Context context, BasePresenter.ResponseHandler handler) {
    String url = RequestBuilder.buildRestUrl(context, API_CATEGORY_PROFILE, API_SUBCATEGORY_STATS,
        API_STATS_CLASSES);
    client.get(url, null, handler);
  }

  public static void getStats(Context context, BasePresenter.ResponseHandler handler, String mode,
      String timeRange) {
    String url = RequestBuilder.buildRestUrl(context, API_CATEGORY_PROFILE, API_SUBCATEGORY_STATS,
        API_STATS_CLASSES);
    if (mode != null && !mode.isEmpty()) {
      url += "&mode=" + mode;
    }
    if (timeRange != null && !timeRange.isEmpty()) {
      url += "&time_range=" + timeRange;
    }
    client.get(url, null, handler);
  }

  public static void getArenaStats(Context context, BasePresenter.ResponseHandler handler,
      String timeRange) {
    String url = RequestBuilder.buildRestUrl(context, API_CATEGORY_PROFILE, API_SUBCATEGORY_STATS,
        API_STATS_ARENA);
    if (timeRange != null && !timeRange.isEmpty()) {
      url += "&time_range=" + timeRange;
    }
    client.get(url, null, handler);
  }

  public static void checkAccess(BasePresenter.ResponseHandler handler, String userName,
      String accesToken) {
    String url = RequestBuilder.buildRestUrl(API_CATEGORY_PROFILE, API_ARENA, userName, accesToken);
    client.get(url, null, handler);
  }
}
