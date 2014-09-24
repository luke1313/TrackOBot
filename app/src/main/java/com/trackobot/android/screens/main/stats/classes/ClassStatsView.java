package com.trackobot.android.screens.main.stats.classes;

import com.trackobot.android.model.stats.Stats;
import com.trackobot.android.util.base.BaseView;

/**
 * Created by lukasolsen on 22/09/14.
 */
public interface ClassStatsView extends BaseView {

  public void onClassStatsLoaded(Stats stats);
}
