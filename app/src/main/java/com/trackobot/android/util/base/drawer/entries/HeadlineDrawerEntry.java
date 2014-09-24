package com.trackobot.android.util.base.drawer.entries;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class HeadlineDrawerEntry extends DrawerEntry {

  private String headline;

  public HeadlineDrawerEntry(String headline) {
    super(null);
    this.headline = headline;
  }

  public String getHeadline() {
    return headline;
  }
}
