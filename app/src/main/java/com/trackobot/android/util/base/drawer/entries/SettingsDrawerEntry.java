package com.trackobot.android.util.base.drawer.entries;

import android.content.Intent;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class SettingsDrawerEntry extends DrawerEntry {

  private String text;

  public SettingsDrawerEntry(Intent i, String text) {
    super(i);
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
