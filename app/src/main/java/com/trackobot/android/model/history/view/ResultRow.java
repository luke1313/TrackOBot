package com.trackobot.android.model.history.view;

import android.content.Context;
import com.trackobot.android.R;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class ResultRow implements MatchDetailsView {

  private String result;

  public ResultRow(String result) {
    this.result = result;
  }

  public String getResultText() {
    return result;
  }
}
