package com.trackobot.android.screens.main.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.model.history.Histories;
import com.trackobot.android.model.history.History;
import com.trackobot.android.screens.IntentBuilder;
import com.trackobot.android.util.base.BaseFragment;
import com.trackobot.android.util.base.BaseRefreshFragment;

/**
 * Created by lukasolsen on 21/09/14.
 */
public class HistoryFragment extends BaseRefreshFragment implements HistoryView {

  private HistoryAdapter adapter;
  private HistoryPresenter presenter;

  private String arenaId;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    presenter = new HistoryPresenter(this);
    adapter = new HistoryAdapter(getActivity());
    setAdatper(adapter);
    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        History history = adapter.getItem(position);
        if (history != null) {
          startActivity(IntentBuilder.buildMatchDetailsIntent(getActivity(), history));
        }
      }
    });
    loadData();
    return view;
  }

  private void loadData() {
    if (arenaId != null && !arenaId.isEmpty()) {
      presenter.loadHistory(getActivity(), arenaId);
    } else {
      presenter.loadHistory(getActivity());
    }
  }

  @Override public void onRefreshTriggered() {
    loadData();
  }

  @Override protected void onErrorViewClicked() {
    loadData();
  }

  @Override public void onHistoriesLoaded(Histories histories) {
    endRefresh();
    adapter.setItems(histories.getHistoryList());
    adapter.notifyDataSetChanged();
  }

  public static HistoryFragment newInstance() {
    HistoryFragment fragment = new HistoryFragment();
    return fragment;
  }

  public void setArenaId(String arenaId) {
    this.arenaId = arenaId;
  }

  public static HistoryFragment newInstance(String arenaId) {
    HistoryFragment fragment = new HistoryFragment();
    fragment.setArenaId(arenaId);
    return fragment;
  }
}
