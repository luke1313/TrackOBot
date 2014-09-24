package com.trackobot.android.model.stats.view;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class HeadlineRow implements StatisticsView {

  String text;

  public HeadlineRow(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
