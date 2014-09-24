package com.trackobot.android;

import android.app.Application;
import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by lukasolsen on 24/09/14.
 */
public class TrackOBotApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();

    JodaTimeAndroid.init(this);
  }

}
