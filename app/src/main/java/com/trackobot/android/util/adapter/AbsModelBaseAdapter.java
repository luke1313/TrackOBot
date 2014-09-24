package com.trackobot.android.util.adapter;

import android.content.Context;
import java.util.List;

/**
 * Created by lukasolsen on 22/09/14.
 */
public abstract class AbsModelBaseAdapter<D> extends AbsBaseAdapter {

  protected List<D> items;

  public AbsModelBaseAdapter(Context context) {
    super(context);
  }

  public void setItems(List<D> items) {
    this.items = items;
  }

  public List<D> getItems() {
    return items;
  }

  @Override
  public int getCount() {
    return items == null ? 0 : items.size();
  }

  @Override
  public D getItem(int position) {
    return items.get(position);
  }
}