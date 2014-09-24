package com.trackobot.android.screens.main.history;

import android.content.Context;
import com.trackobot.android.http.TrackOBotRestClient;
import com.trackobot.android.model.history.Histories;
import com.trackobot.android.util.base.BasePresenter;

/**
 * Created by lukasolsen on 21/09/14.
 */
public class HistoryPresenter extends BasePresenter<HistoryView> {

  public HistoryPresenter(HistoryView view) {
    super(view);
  }

  public void loadHistory(Context context) {
    TrackOBotRestClient.getHistory(context, new ResponseHandler<Histories>(Histories.class) {
      @Override public void onSucces(Histories histories) {
        if (isViewAttached()) {
          getView().onHistoriesLoaded(histories);
        }
      }
    });
  }

  public void loadHistory(Context context, String arenaId) {
    TrackOBotRestClient.getHistoryOfArena(context, arenaId,
        new ResponseHandler<Histories>(Histories.class) {
          @Override public void onSucces(Histories histories) {
            if (isViewAttached()) {
              getView().onHistoriesLoaded(histories);
            }
          }
        });
  }
}
