package com.trackobot.android.screens.main.arena;

import com.trackobot.android.model.arena.Arenas;
import com.trackobot.android.util.base.BaseView;

/**
 * Created by lukasolsen on 22/09/14.
 */
public interface ArenaView extends BaseView {

  public void onArenasLoaded(Arenas arenas);
}
