package com.trackobot.android.screens.matchdetails;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.model.history.History;
import com.trackobot.android.util.ClassIcon;
import com.trackobot.android.util.base.BaseActivity;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class MatchDetailsActivity extends BaseActivity {

  @InjectView(R.id.matchdetails_activity_player1) TextView player1;
  @InjectView(R.id.matchdetails_activity_player1_starting) TextView player1Start;
  @InjectView(R.id.matchdetails_activity_player2) TextView player2;
  @InjectView(R.id.matchdetails_activity_player2_starting) TextView player2Start;
  @InjectView(R.id.matchdetails_activity_player1_icon) ClassIcon player1Icon;
  @InjectView(R.id.matchdetails_activity_player2_icon) ClassIcon player2Icon;

  @InjectView(R.id.matchdetails_activity_list) ListView list;

  public static final String INTENT_EXTRA_HISTORY = "history";

  private History history;
  private MatchDetailsAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getActionBar().setIcon(R.drawable.ic_back);
    getActionBar().setDisplayShowTitleEnabled(false);

    setHomeAsUpEnabled(new HomeClickedListener() {
      @Override public void onHomeClicked() {
        finish();
      }
    });

    adapter = new MatchDetailsAdapter(getBaseContext());
    list.setAdapter(adapter);
    history = getIntent().getExtras().getParcelable(INTENT_EXTRA_HISTORY);
    player1.setText(history.getHero());
    if (history.isCoin()) {
      player1Start.setText("first");
      player2Start.setText("second");
    } else {
      player1Start.setText("second");
      player2Start.setText("first");
    }
    player1Icon.setClassName(history.getHero());
    player2Icon.setClassName(history.getOpponent());
    player2.setText(history.getOpponent());
    adapter.setItems(history.createList());
    adapter.notifyDataSetChanged();
  }

  @Override public int getLayoutId() {
    return R.layout.matchdetails_activity;
  }
}
