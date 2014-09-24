package com.trackobot.android.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by lukasolsen on 21/09/14.
 */
public abstract class AbsBaseAdapter extends BaseAdapter {

  /**
   * The inflater for
   */
  protected LayoutInflater inflater;
  protected Context context;

  public AbsBaseAdapter(Context context) {
    this.context = context;
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    int type = getItemViewType(position);
    if (convertView == null) {
      convertView = newView(type, parent);
    }
    bindView(position, type, convertView);
    return convertView;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  /** Create a new instance of a view for the specified {@code type}. */
  public abstract View newView(int type, ViewGroup parent);

  /** Bind the data for the specified {@code position} to the {@code view}. */
  public abstract void bindView(int position, int type, View view);
}
