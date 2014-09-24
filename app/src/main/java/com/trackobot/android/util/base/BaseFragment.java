package com.trackobot.android.util.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import com.trackobot.android.R;

/**
 * Created by lukasolsen on 21/09/14.
 */
public abstract class BaseFragment extends Fragment implements BaseView {

  private FrameLayout contentView;
  private View loadingView;
  private View errorView;

  private boolean contentLoaded = false;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.base_fragment, null);
    contentLoaded = false;
    findViews(view);
    View content = inflater.inflate(getLayoutId(), contentView, false);
    contentView.addView(content);
    errorView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onErrorViewClicked();
      }
    });
    ButterKnife.inject(this, view);
    return view;
  }

  protected void findViews(View view) {
    contentView = (FrameLayout) view.findViewById(R.id.contentView);
    loadingView = view.findViewById(R.id.loadingView);
    errorView = view.findViewById(R.id.errorView);
  }

  protected abstract void onErrorViewClicked();

  public abstract int getLayoutId();

  @Override public void showError() {
    setContentVisibility(View.GONE);
    setLoadingVisibility(View.GONE);
    setErrorVisibility(View.VISIBLE);
  }

  @Override public void showLoading() {
    if (!contentLoaded) {
      setContentVisibility(View.GONE);
      setErrorVisibility(View.GONE);
      setLoadingVisibility(View.VISIBLE);
    }
  }

  @Override public void showContent() {
    contentLoaded = true;
    setLoadingVisibility(View.GONE);
    setErrorVisibility(View.GONE);
    setContentVisibility(View.VISIBLE);
  }

  private void setLoadingVisibility(int visibility) {
    setViewVisibility(loadingView, visibility);
  }

  private void setErrorVisibility(int visibility) {
    setViewVisibility(errorView, visibility);
  }

  private void setContentVisibility(int visibility) {
    setViewVisibility(contentView, visibility);
  }

  private void setViewVisibility(View view, int visibility) {
    if (view != null) {
      view.setVisibility(visibility);
    }
  }

  protected boolean isContentLoaded() {
    return contentLoaded;
  }
}
