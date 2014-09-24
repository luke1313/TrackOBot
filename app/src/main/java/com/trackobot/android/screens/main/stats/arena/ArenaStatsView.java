package com.trackobot.android.screens.main.stats.arena;

import com.trackobot.android.model.stats.Stats;
import com.trackobot.android.model.stats.StatsWrapper;
import com.trackobot.android.util.base.BaseView;

/**
 * Created by lukasolsen on 23/09/14.
 */
public interface ArenaStatsView extends BaseView {

  public void onArenaStatsLoaded(Stats stats);
}
