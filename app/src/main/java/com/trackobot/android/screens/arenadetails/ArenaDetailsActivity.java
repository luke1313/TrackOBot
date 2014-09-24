package com.trackobot.android.screens.arenadetails;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.screens.main.history.HistoryFragment;
import com.trackobot.android.util.base.BaseActivity;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class ArenaDetailsActivity extends BaseActivity {

  public static final String INTENT_EXTRA_TITLE = "title";
  public static final String INTENT_EXTRA_ARENA_ID = "arenaId";

  @InjectView(R.id.arena_details_fragment_container) FrameLayout fragmentContainer;

  private String title;
  private String arenaId;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    arenaId = getIntent().getStringExtra(INTENT_EXTRA_ARENA_ID);
    title = getIntent().getStringExtra(INTENT_EXTRA_TITLE);
    getActionBar().setTitle(title);
    getActionBar().setIcon(R.drawable.ic_back);

    setHomeAsUpEnabled(new HomeClickedListener() {
      @Override public void onHomeClicked() {
        finish();
      }
    });

    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.arena_details_fragment_container, HistoryFragment.newInstance(arenaId));
    transaction.commit();
  }

  @Override public int getLayoutId() {
    return R.layout.arena_details_activity;
  }
}
