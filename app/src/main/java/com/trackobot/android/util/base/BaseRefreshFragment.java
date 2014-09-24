package com.trackobot.android.util.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.trackobot.android.R;
import com.trackobot.android.util.adapter.AbsBaseAdapter;

/**
 * Created by lukasolsen on 22/09/14.
 */
public abstract class BaseRefreshFragment extends BaseFragment {

  private ListView list;
  private SwipeRefreshLayout refreshLayout;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    list = (ListView) view.findViewById(R.id.base_refreshfragment_list);
    refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.base_refreshfragment_refresh);
    refreshLayout.setColorScheme(R.color.loading_sheme_1, R.color.loading_sheme_2,
        R.color.loading_sheme_3, R.color.loading_sheme_4);
    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        startRefresh();
        onRefreshTriggered();
      }
    });
    return view;
  }

  protected ListView getListView() {
    return list;
  }

  public abstract void onRefreshTriggered();

  @Override public int getLayoutId() {
    return R.layout.base_refreshfragment;
  }

  protected void setAdatper(AbsBaseAdapter adapter) {
    if (list != null) {
      list.setAdapter(adapter);
    }
  }

  protected void endRefresh() {
    refreshLayout.setRefreshing(false);
  }

  protected void startRefresh() {
    refreshLayout.setRefreshing(true);
  }
}
