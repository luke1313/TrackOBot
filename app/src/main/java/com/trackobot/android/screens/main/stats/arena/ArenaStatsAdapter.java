package com.trackobot.android.screens.main.stats.arena;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.model.stats.Stat;
import com.trackobot.android.model.stats.view.DividerRow;
import com.trackobot.android.model.stats.view.GraphRow;
import com.trackobot.android.model.stats.view.OverallChartRow;
import com.trackobot.android.model.stats.view.SpaceRow;
import com.trackobot.android.model.stats.view.StatRow;
import com.trackobot.android.model.stats.view.StatisticsView;
import com.trackobot.android.util.ClassIcon;
import com.trackobot.android.util.adapter.AbsModelBaseAdapter;
import com.trackobot.android.util.base.BaseViewHolder;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class ArenaStatsAdapter extends AbsModelBaseAdapter<StatisticsView> {

  private static final int VIEWTYPE_NOT_FOUND = -1;

  private static final int VIEWTYPE_OVERVIEW = 0;
  private static final int VIEWTYPE_GRAPH = 1;
  private static final int VIEWTYPE_STAT = 2;
  private static final int VIEWTYPE_SPACE = 3;
  private static final int VIEWTYPE_DIVIDER = 4;

  private static final int VIEWTYPE_COUNT = 5;

  class ArenaStatsOverallViewHolder extends BaseViewHolder {

    @InjectView(R.id.arena_stats_row_overall_average) TextView average;
    @InjectView(R.id.arena_stats_row_overall_runs) TextView runs;
    @InjectView(R.id.arena_stats_row_overall_wins) TextView wins;
    @InjectView(R.id.arena_stats_row_overall_losses) TextView losses;
    @InjectView(R.id.arena_stats_row_overall_winrate) TextView winrate;

    public ArenaStatsOverallViewHolder(View view) {
      super(view);
    }
  }

  class ArenaClassStatsViewHolder extends BaseViewHolder {

    @InjectView(R.id.arena_stats_row_stat_class_name) TextView className;
    @InjectView(R.id.arena_stats_row_stat_average) TextView average;
    @InjectView(R.id.arena_stats_row_stat_runs) TextView runs;
    @InjectView(R.id.arena_stats_row_stat_wins) TextView wins;
    @InjectView(R.id.arena_stats_row_stat_losses) TextView losses;
    @InjectView(R.id.arena_stats_row_stat_winrate) TextView winrate;
    @InjectView(R.id.arena_stats_row_stat_class_icon) ClassIcon icon;

    public ArenaClassStatsViewHolder(View view) {
      super(view);
    }
  }

  public ArenaStatsAdapter(Context context) {
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
      case VIEWTYPE_GRAPH: {
        view = inflater.inflate(R.layout.arena_stats_row_graph, parent, false);
        break;
      }
      case VIEWTYPE_OVERVIEW: {
        view = inflater.inflate(R.layout.arena_stats_row_overall, parent, false);
        view.setTag(new ArenaStatsOverallViewHolder(view));
        break;
      }
      case VIEWTYPE_STAT: {
        view = inflater.inflate(R.layout.arena_stats_row_class, parent, false);
        view.setTag(new ArenaClassStatsViewHolder(view));
        break;
      }
    }
    return view;
  }

  @Override public void bindView(int position, int type, View view) {
    switch (type) {
      case VIEWTYPE_GRAPH: {
        break;
      }
      case VIEWTYPE_OVERVIEW: {
        ArenaStatsOverallViewHolder holder = (ArenaStatsOverallViewHolder) view.getTag();
        OverallChartRow row = (OverallChartRow) getItem(position);
        Stat stat = row.getStat();
        holder.average.setText(stat.getAverage());
        holder.losses.setText(stat.getLosses() + "");
        holder.wins.setText(stat.getWins() + "");
        holder.winrate.setText(stat.getWinPercentage());
        holder.runs.setText(stat.getRuns() + "");
        break;
      }
      case VIEWTYPE_STAT: {
        ArenaClassStatsViewHolder holder = (ArenaClassStatsViewHolder) view.getTag();
        StatRow row = (StatRow) getItem(position);
        Stat stat = row.getStat();
        holder.average.setText(stat.getAverage());
        holder.runs.setText(stat.getRuns() + "");
        holder.className.setText(row.getClassName());
        holder.losses.setText(stat.getLosses() + "");
        holder.wins.setText(stat.getWins() + "");
        holder.winrate.setText(stat.getWinPercentage());
        holder.icon.setClassName(row.getClassName());
        break;
      }
    }
  }

  @Override public int getItemViewType(int position) {
    StatisticsView item = getItem(position);
    if (item instanceof SpaceRow) {
      return VIEWTYPE_SPACE;
    }
    if (item instanceof DividerRow) {
      return VIEWTYPE_DIVIDER;
    }
    if (item instanceof OverallChartRow) {
      return VIEWTYPE_OVERVIEW;
    }
    if (item instanceof StatRow) {
      return VIEWTYPE_STAT;
    }
    if (item instanceof GraphRow) {
      return VIEWTYPE_GRAPH;
    }
    return VIEWTYPE_NOT_FOUND;
  }

  @Override public int getViewTypeCount() {
    return VIEWTYPE_COUNT;
  }
}
