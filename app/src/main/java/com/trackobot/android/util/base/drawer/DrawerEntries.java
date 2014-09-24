package com.trackobot.android.util.base.drawer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.trackobot.android.screens.setup.SetupActivity;
import com.trackobot.android.util.base.drawer.entries.DividerDrawerEntry;
import com.trackobot.android.util.base.drawer.entries.DrawerEntry;
import com.trackobot.android.util.base.drawer.entries.HeadlineDrawerEntry;
import com.trackobot.android.util.base.drawer.entries.SettingsDrawerEntry;
import com.trackobot.android.util.base.drawer.entries.ShareDrawerEntry;
import com.trackobot.android.util.base.drawer.entries.SpaceDrawerEntry;
import com.trackobot.android.util.base.drawer.entries.UserDrawerEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class DrawerEntries {

  public static final String playstoreUrl = "bla";

  public static List<DrawerEntry> getDrawerEntries(Context context) {
    List<DrawerEntry> entries = new ArrayList<DrawerEntry>();
    entries.add(new HeadlineDrawerEntry("Account"));
    entries.add(new UserDrawerEntry());
    entries.add(new DividerDrawerEntry());
    entries.add(new SettingsDrawerEntry(new Intent(context, SetupActivity.class),
        "Change Account-Settings"));
    entries.add(new SpaceDrawerEntry());
    entries.add(new HeadlineDrawerEntry("Other"));
    entries.add(new ShareDrawerEntry(new Intent(Intent.ACTION_SEND), "Share this App"));
    entries.add(new DividerDrawerEntry());
    entries.add(new SettingsDrawerEntry(null, "Imprint"));
    entries.add(new DividerDrawerEntry());
    entries.add(new SettingsDrawerEntry(null, "Open-Source-Licenses"));
    return entries;
  }
}
