package com.trackobot.android.screens.main.arena;

import android.content.Context;
import com.trackobot.android.http.TrackOBotRestClient;
import com.trackobot.android.model.arena.Arenas;
import com.trackobot.android.model.history.Histories;
import com.trackobot.android.util.base.BasePresenter;
import com.trackobot.android.util.base.BaseView;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class ArenaPresenter extends BasePresenter<ArenaView> {

  public ArenaPresenter(ArenaView view) {
    super(view);
  }

  public void loadArenas(Context context) {
    TrackOBotRestClient.getArena(context, new ResponseHandler<Arenas>(Arenas.class) {
      @Override public void onSucces(Arenas arenas) {
        if (isViewAttached()) {
          getView().onArenasLoaded(arenas);
        }
      }
    });
  }
}
