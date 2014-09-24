package com.trackobot.android.screens.main.stats.arena;

import android.content.Context;
import com.trackobot.android.http.TrackOBotRestClient;
import com.trackobot.android.model.stats.StatsWrapper;
import com.trackobot.android.util.base.BasePresenter;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class ArenaStatsPresenter extends BasePresenter<ArenaStatsView> {

  public ArenaStatsPresenter(ArenaStatsView view) {
    super(view);
  }

  public void loadArenaStats(Context context, String timeMode) {
    TrackOBotRestClient.getArenaStats(context,
        new ResponseHandler<StatsWrapper>(StatsWrapper.class) {
          @Override public void onSucces(StatsWrapper statsWrapper) {
            if (isViewAttached()) {
              getView().onArenaStatsLoaded(statsWrapper.getStats());
            }
          }
        }, timeMode);
  }
}
