package com.trackobot.android.screens.main.history;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.model.history.History;
import com.trackobot.android.util.ClassIcon;
import com.trackobot.android.util.StringUtil;
import com.trackobot.android.util.adapter.AbsModelBaseAdapter;
import com.trackobot.android.util.base.BaseViewHolder;
import com.trackobot.android.util.time.TimeUtils;
import net.danlew.android.joda.DateUtils;
import org.joda.time.DateTime;

/**
 * Created by lukasolsen on 21/09/14.
 */
public class HistoryAdapter extends AbsModelBaseAdapter<History> {

  class HistoryViewHolder extends BaseViewHolder {

    @InjectView(R.id.history_row_opponent) TextView opponent;
    @InjectView(R.id.history_row_duration) TextView duration;
    @InjectView(R.id.history_row_date) TextView date;
    @InjectView(R.id.history_row_self) TextView self;
    @InjectView(R.id.history_row_mode) TextView mode;
    @InjectView(R.id.history_row_result) TextView result;
    @InjectView(R.id.history_row_classicon_player1) ClassIcon player1Icon;
    @InjectView(R.id.history_row_classicon_player2) ClassIcon player2Icon;

    public HistoryViewHolder(View view) {
      super(view);
    }
  }

  public HistoryAdapter(Context context) {
    super(context);
  }

  @Override public View newView(int type, ViewGroup parent) {
    View view = inflater.inflate(R.layout.history_row, parent, false);
    HistoryViewHolder holder = new HistoryViewHolder(view);
    view.setTag(holder);
    return view;
  }

  @Override public void bindView(int position, int type, View view) {
    HistoryViewHolder holder = (HistoryViewHolder) view.getTag();
    History history = getItem(position);
    holder.duration.setText(history.getDurationString());
    holder.mode.setText(StringUtil.capitalize(history.getMode()));
    holder.opponent.setText(history.getOpponent());
    holder.self.setText(history.getHero());
    holder.result.setText(history.getResult());
    holder.player1Icon.setClassName(history.getHero());
    holder.player2Icon.setClassName(history.getOpponent());
    DateTime dateTime = TimeUtils.createDateFromString(history.getAdded());
    if (dateTime != null) {
      holder.date.setText(DateUtils.getRelativeTimeSpanString(context, dateTime));
    }
  }
}
