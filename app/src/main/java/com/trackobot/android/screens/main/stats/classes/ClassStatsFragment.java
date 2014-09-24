package com.trackobot.android.screens.main.stats.classes;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.http.RequestBuilder;
import com.trackobot.android.model.stats.Stats;
import com.trackobot.android.util.SpinnerItemSelectedListener;
import com.trackobot.android.util.base.BaseFragment;
import com.trackobot.android.util.base.BaseRefreshFragment;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class ClassStatsFragment extends BaseFragment implements ClassStatsView {

  @InjectView(R.id.class_stats_time_spinner) Spinner timeSpinner;
  @InjectView(R.id.class_stats_mode_spinner) Spinner modeSpinner;
  @InjectView(R.id.class_stats_fragment_list) ListView list;
  @InjectView(R.id.class_stats_fragment_refresh) SwipeRefreshLayout refreshLayout;

  private ClassStatsPresenter presenter;
  private ClassStatsAdapter adapter;

  private String timeMode, modeMode;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    presenter = new ClassStatsPresenter(this);
    adapter = new ClassStatsAdapter(getActivity());
    presenter.loadClassStats(getActivity(), timeMode, modeMode);
    timeSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener() {
      @Override public void onUserItemSelected(int position) {
        timeMode = RequestBuilder.TIME_MODES[position];
        onSpinnerItemChanged();
      }
    });
    modeSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener() {
      @Override public void onUserItemSelected(int position) {
        modeMode = RequestBuilder.MODE_MODES[position];
        onSpinnerItemChanged();
      }
    });
    list.setAdapter(adapter);
    refreshLayout.setColorScheme(R.color.loading_sheme_1, R.color.loading_sheme_2,
        R.color.loading_sheme_3, R.color.loading_sheme_4);
    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        onRefreshTriggered();
      }
    });
    return view;
  }

  private void onSpinnerItemChanged() {
    refreshLayout.setRefreshing(true);
    presenter.loadClassStats(getActivity(), timeMode, modeMode);
  }

  public void onRefreshTriggered() {
    presenter.loadClassStats(getActivity(), timeMode, modeMode);
  }

  @Override protected void onErrorViewClicked() {

  }

  @Override public int getLayoutId() {
    return R.layout.class_stats_fragment;
  }

  public static ClassStatsFragment newInstance() {
    ClassStatsFragment fragment = new ClassStatsFragment();
    return fragment;
  }

  @Override public void onClassStatsLoaded(Stats stats) {
    refreshLayout.setRefreshing(false);
    adapter.setItems(stats.getList());
    adapter.notifyDataSetChanged();
  }
}
