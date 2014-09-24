package com.trackobot.android.model.stats.view;

import com.trackobot.android.model.stats.Stat;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class StatRow implements StatisticsView {

  String className;
  Stat stat;
  String modeLine;

  public StatRow(String className, Stat stat) {
    this.className = className;
    this.stat = stat;
  }

  public StatRow(String className, Stat stat, String modeLine) {
    this.className = className;
    this.stat = stat;
    this.modeLine = modeLine;
  }

  public String getClassName() {
    return className;
  }

  public Stat getStat() {
    return stat;
  }

  public String getModeLine() {
    return modeLine;
  }
}
