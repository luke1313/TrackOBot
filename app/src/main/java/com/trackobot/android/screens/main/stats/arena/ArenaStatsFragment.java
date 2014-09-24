package com.trackobot.android.screens.main.stats.arena;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.http.RequestBuilder;
import com.trackobot.android.model.stats.Stats;
import com.trackobot.android.util.SpinnerItemSelectedListener;
import com.trackobot.android.util.base.BaseFragment;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class ArenaStatsFragment extends BaseFragment implements ArenaStatsView {

  @InjectView(R.id.arena_stats_fragment_list) ListView list;
  @InjectView(R.id.arena_stats_fragment_refresh) SwipeRefreshLayout refreshLayout;
  @InjectView(R.id.arena_stats_time_spinner) Spinner timeSpinner;

  private ArenaStatsPresenter presenter;
  private ArenaStatsAdapter adapter;

  private String timeMode;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    presenter = new ArenaStatsPresenter(this);
    refreshLayout.setColorScheme(R.color.loading_sheme_1, R.color.loading_sheme_2,
        R.color.loading_sheme_3, R.color.loading_sheme_4);
    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        onRefreshTriggered();
      }
    });
    timeSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener() {
      @Override public void onUserItemSelected(int position) {
        timeMode = RequestBuilder.TIME_MODES[position];
        onSpinnerItemChanged();
      }
    });
    presenter.loadArenaStats(getActivity(), timeMode);
    adapter = new ArenaStatsAdapter(getActivity());
    list.setAdapter(adapter);
    return view;
  }

  private void onSpinnerItemChanged() {
    refreshLayout.setRefreshing(true);
    presenter.loadArenaStats(getActivity(), timeMode);
  }

  private void onRefreshTriggered() {
    presenter.loadArenaStats(getActivity(), timeMode);
  }

  @Override protected void onErrorViewClicked() {

  }

  @Override public int getLayoutId() {
    return R.layout.arena_stats_fragment;
  }

  public static ArenaStatsFragment newInstance() {
    ArenaStatsFragment fragment = new ArenaStatsFragment();

    return fragment;
  }

  @Override public void onArenaStatsLoaded(Stats stats) {
    refreshLayout.setRefreshing(false);
    adapter.setItems(stats.getList());
    adapter.notifyDataSetChanged();
  }
}
