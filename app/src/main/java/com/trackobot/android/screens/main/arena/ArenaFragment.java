package com.trackobot.android.screens.main.arena;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.trackobot.android.R;
import com.trackobot.android.model.arena.Arena;
import com.trackobot.android.model.arena.Arenas;
import com.trackobot.android.screens.IntentBuilder;
import com.trackobot.android.util.base.BaseRefreshFragment;

/**
 * Created by lukasolsen on 21/09/14.
 */
public class ArenaFragment extends BaseRefreshFragment implements ArenaView {

  private ArenaPresenter presenter;
  private ArenaAdapter adapter;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    presenter = new ArenaPresenter(this);
    presenter.loadArenas(getActivity());
    adapter = new ArenaAdapter(getActivity());
    setAdatper(adapter);
    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Arena arena = adapter.getItem(position);
        if (arena != null) {
          String arenaId = arena.getId();
          String title = arena.buildTitle();
          startActivity(IntentBuilder.buildArenaDetailsIntent(getActivity(), arenaId, title));
        }
      }
    });
    return view;
  }

  @Override public void onRefreshTriggered() {
    presenter.loadArenas(getActivity());
  }

  @Override protected void onErrorViewClicked() {

  }

  @Override public int getLayoutId() {
    return R.layout.base_refreshfragment;
  }

  public static ArenaFragment newInstance() {
    ArenaFragment fragment = new ArenaFragment();
    return fragment;
  }

  @Override public void onArenasLoaded(Arenas arenas) {
    endRefresh();
    adapter.setItems(arenas.getArenas());
    adapter.notifyDataSetChanged();
  }
}
