package com.trackobot.android.util.base;

import android.view.View;
import butterknife.ButterKnife;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class BaseViewHolder {

  public View itemView;

  public BaseViewHolder(View view) {
    ButterKnife.inject(this, view);
    itemView = view;
  }
}
