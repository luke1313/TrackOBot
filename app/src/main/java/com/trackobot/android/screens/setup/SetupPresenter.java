package com.trackobot.android.screens.setup;

import com.trackobot.android.http.TrackOBotRestClient;
import com.trackobot.android.model.arena.Arenas;
import com.trackobot.android.util.base.BasePresenter;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class SetupPresenter extends BasePresenter<SetupView> {

  public SetupPresenter(SetupView view) {
    super(view);
  }

  public void checkAccess(String userName, String accesToken) {
    TrackOBotRestClient.checkAccess(new ResponseHandler<Arenas>(Arenas.class) {
      @Override public void onSucces(Arenas arenas) {

      }
    }, userName, accesToken);
  }
}
