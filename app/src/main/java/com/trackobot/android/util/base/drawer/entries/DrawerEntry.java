package com.trackobot.android.util.base.drawer.entries;

import android.content.Intent;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class DrawerEntry {

  private Intent i;

  public DrawerEntry(Intent i) {
    this.i = i;
  }

  public Intent getIntent() {
    return i;
  }

}
