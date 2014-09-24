package com.trackobot.android.screens.main.arena;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.model.arena.Arena;
import com.trackobot.android.util.ClassIcon;
import com.trackobot.android.util.adapter.AbsModelBaseAdapter;
import com.trackobot.android.util.base.BaseViewHolder;
import net.danlew.android.joda.DateUtils;
import org.joda.time.DateTime;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class ArenaAdapter extends AbsModelBaseAdapter<Arena> {

  class ArenaViewHolder extends BaseViewHolder {
    @InjectView(R.id.arena_row_class_icon) ClassIcon icon;
    @InjectView(R.id.arena_row_wins) TextView result;
    @InjectView(R.id.arena_row_class) TextView className;
    @InjectView(R.id.arena_row_date) TextView date;

    public ArenaViewHolder(View view) {
      super(view);
    }
  }

  public ArenaAdapter(Context context) {
    super(context);
  }

  @Override public View newView(int type, ViewGroup parent) {
    View view = inflater.inflate(R.layout.arena_row, parent, false);
    view.setTag(new ArenaViewHolder(view));
    return view;
  }

  @Override public void bindView(int position, int type, View view) {
    ArenaViewHolder holder = (ArenaViewHolder) view.getTag();
    Arena arena = getItem(position);
    holder.className.setText(arena.getHero());
    holder.result.setText(arena.getResultString());
    holder.icon.setClassName(arena.getHero());
    DateTime dateTime = arena.getDateString();
    if (dateTime != null) {
      holder.date.setText(DateUtils.getRelativeTimeSpanString(context, dateTime));
    }
  }
}
