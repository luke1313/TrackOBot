package com.trackobot.android.util;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by lukasolsen on 22/09/14.
 */
public abstract class SpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {

  private boolean autoSelection = false;

  @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    if (!autoSelection) {
      autoSelection = true;
    } else {
      onUserItemSelected(position);
    }
  }

  public abstract void onUserItemSelected(int position);

  @Override public void onNothingSelected(AdapterView<?> parent) {

  }
}
