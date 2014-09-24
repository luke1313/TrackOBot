package com.trackobot.android.screens.main.stats.classes;

import android.content.Context;
import com.trackobot.android.http.TrackOBotRestClient;
import com.trackobot.android.model.stats.ClassStats;
import com.trackobot.android.model.stats.Stats;
import com.trackobot.android.model.stats.StatsWrapper;
import com.trackobot.android.util.base.BasePresenter;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class ClassStatsPresenter extends BasePresenter<ClassStatsView> {

  public ClassStatsPresenter(ClassStatsView view) {
    super(view);
  }

  public void loadClassStats(Context context, String timeMode, String modeMode) {
    TrackOBotRestClient.getStats(context, new ResponseHandler<StatsWrapper>(StatsWrapper.class) {
      @Override public void onSucces(StatsWrapper stats) {
        if (isViewAttached()) {
          getView().onClassStatsLoaded(stats.getStats());
        }
      }
    }, modeMode, timeMode);
  }
}
