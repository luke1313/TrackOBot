package com.trackobot.android.screens;

import android.content.Context;
import android.content.Intent;
import com.trackobot.android.model.history.History;
import com.trackobot.android.screens.arenadetails.ArenaDetailsActivity;
import com.trackobot.android.screens.matchdetails.MatchDetailsActivity;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class IntentBuilder {

  public static Intent buildMatchDetailsIntent(Context context, History history) {
    Intent i = new Intent(context, MatchDetailsActivity.class);
    i.putExtra(MatchDetailsActivity.INTENT_EXTRA_HISTORY, history);
    return i;
  }

  public static Intent buildArenaDetailsIntent(Context context, String arenaId, String title) {
    Intent i = new Intent(context, ArenaDetailsActivity.class);
    i.putExtra(ArenaDetailsActivity.INTENT_EXTRA_TITLE, title);
    i.putExtra(ArenaDetailsActivity.INTENT_EXTRA_ARENA_ID, arenaId);
    return i;
  }
}
