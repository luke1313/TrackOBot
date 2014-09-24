package com.trackobot.android.screens.main.history;

import com.trackobot.android.model.history.Histories;
import com.trackobot.android.util.base.BaseView;

/**
 * Created by lukasolsen on 21/09/14.
 */
public interface HistoryView extends BaseView {

  public void onHistoriesLoaded(Histories histories);

}
