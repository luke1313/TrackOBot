package com.trackobot.android.screens.main.stats.classes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.model.stats.Stat;
import com.trackobot.android.model.stats.view.DividerRow;
import com.trackobot.android.model.stats.view.OverallChartRow;
import com.trackobot.android.model.stats.view.SpaceRow;
import com.trackobot.android.model.stats.view.StatRow;
import com.trackobot.android.model.stats.view.StatisticsView;
import com.trackobot.android.util.ClassIcon;
import com.trackobot.android.util.adapter.AbsModelBaseAdapter;
import com.trackobot.android.util.base.BaseViewHolder;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class ClassStatsAdapter extends AbsModelBaseAdapter<StatisticsView> {

  private static final int VIEWTYPE_NOT_FOUND = -1;

  private static final int VIEWTYPE_OVERALL = 0;
  private static final int VIEWTYPE_CLASS_STAT = 1;
  private static final int VIEWTYPE_SPACE = 2;
  private static final int VIEWTYPE_DIVIDER = 3;

  private static final int VIEWTYPE_COUNT = 4;

  class OverAllViewHolder extends BaseViewHolder {

    @InjectView(R.id.class_stats_row_overall_wins) TextView wins;
    @InjectView(R.id.class_stats_row_overall_losses) TextView losses;
    @InjectView(R.id.class_stats_row_overall_winrate) TextView winrate;

    public OverAllViewHolder(View view) {
      super(view);
    }
  }

  class StatViewHolder extends BaseViewHolder {

    @InjectView(R.id.class_stats_row_stat_class) TextView className;
    @InjectView(R.id.class_stats_row_stat_wins) TextView wins;
    @InjectView(R.id.class_stats_row_stat_losses) TextView losses;
    @InjectView(R.id.class_stats_row_stat_winrate) TextView winrate;
    @InjectView(R.id.class_stats_row_stat_classicon) ClassIcon icon;
    @InjectView(R.id.class_stats_row_stat_as) TextView mode;

    public StatViewHolder(View view) {
      super(view);
    }
  }

  class HeadlineViewHolder extends BaseViewHolder {

    @InjectView(R.id.class_stats_headline) TextView headline;

    public HeadlineViewHolder(View view) {
      super(view);
    }
  }

  public ClassStatsAdapter(Context context) {
    super(context);
  }

  @Override public View newView(int type, ViewGroup parent) {
    View view = null;
    switch (type) {
      case VIEWTYPE_DIVIDER: {
        view = inflater.inflate(R.layout.row_divider, parent, false);
        break;
      }
      case VIEWTYPE_SPACE: {
        view = inflater.inflate(R.layout.row_space, parent, false);
        break;
      }
      case VIEWTYPE_OVERALL: {
        view = inflater.inflate(R.layout.class_stats_overall, parent, false);
        view.setTag(new OverAllViewHolder(view));
        break;
      }
      case VIEWTYPE_CLASS_STAT: {
        view = inflater.inflate(R.layout.class_stats_row_stat, parent, false);
        view.setTag(new StatViewHolder(view));
        break;
      }
    }
    return view;
  }

  @Override public void bindView(int position, int type, View view) {
    switch (type) {

      case VIEWTYPE_OVERALL: {
        OverallChartRow row = (OverallChartRow) getItem(position);
        OverAllViewHolder holder = (OverAllViewHolder) view.getTag();
        Stat stat = row.getStat();
        holder.losses.setText(stat.getLosses() + "");
        holder.winrate.setText(stat.getWinPercentage());
        holder.wins.setText(stat.getWins() + "");
        break;
      }
      case VIEWTYPE_CLASS_STAT: {
        StatViewHolder holder = (StatViewHolder) view.getTag();
        StatRow row = (StatRow) getItem(position);
        Stat stat = row.getStat();
        if (stat != null) {
          holder.className.setText(row.getClassName());
          holder.losses.setText(stat.getLosses() + "");
          holder.wins.setText(stat.getWins() + "");
          holder.winrate.setText(stat.getWinPercentage());
          holder.icon.setClassName(row.getClassName());
          holder.mode.setText(row.getModeLine());
        }
        break;
      }
    }
  }

  @Override public int getItemViewType(int position) {
    StatisticsView item = getItem(position);
    if (item instanceof OverallChartRow) {
      return VIEWTYPE_OVERALL;
    }
    if (item instanceof StatRow) {
      return VIEWTYPE_CLASS_STAT;
    }
    if (item instanceof DividerRow) {
      return VIEWTYPE_DIVIDER;
    }
    if (item instanceof SpaceRow) {
      return VIEWTYPE_SPACE;
    }
    return VIEWTYPE_NOT_FOUND;
  }

  @Override public int getViewTypeCount() {
    return VIEWTYPE_COUNT;
  }
}
