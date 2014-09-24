package com.trackobot.android.model.stats.view;

import com.trackobot.android.model.stats.Stat;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class OverallChartRow extends StatRow {

  private float winPercentage;
  private float losePercentage;

  public OverallChartRow(Stat stat) {
    super(null, stat);
    winPercentage = stat.getWinNumberPercentage();
    losePercentage = 100.0f - winPercentage;
  }

  public float getWinPercentage() {
    return winPercentage;
  }

  public float getLosePercentage() {
    return losePercentage;
  }
}
