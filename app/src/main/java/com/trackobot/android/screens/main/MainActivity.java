package com.trackobot.android.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.InjectView;
import com.astuetz.PagerSlidingTabStrip;
import com.trackobot.android.R;
import com.trackobot.android.model.user.UserUtils;
import com.trackobot.android.screens.main.arena.ArenaFragment;
import com.trackobot.android.screens.main.history.HistoryFragment;
import com.trackobot.android.screens.main.stats.arena.ArenaStatsFragment;
import com.trackobot.android.screens.main.stats.classes.ClassStatsFragment;
import com.trackobot.android.screens.setup.SetupActivity;
import com.trackobot.android.util.base.BaseActivity;

public class MainActivity extends BaseActivity {

  @InjectView(R.id.activity_main_pager) ViewPager pager;
  @InjectView(R.id.activity_main_tabs) PagerSlidingTabStrip tabs;

  private MainPagerAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (!UserUtils.isUserLoggedin(getBaseContext())) {
      Intent i = new Intent(MainActivity.this, SetupActivity.class);
      startActivity(i);
      finish();
    }
    getActionBar().setIcon(R.drawable.ic_menu);
    findViewById(android.R.id.home).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        toggleDrawer();
      }
    });
    adapter = new MainPagerAdapter(getSupportFragmentManager());
    pager.setAdapter(adapter);
    tabs.setViewPager(pager);
    tabs.setTextColorResource(R.color.white);
  }

  @Override public int getLayoutId() {
    return R.layout.main_activity;
  }

  public class MainPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;

    public MainPagerAdapter(FragmentManager fm) {
      super(fm);
      TITLES = getResources().getStringArray(R.array.pager_titles);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return TITLES[position];
    }

    @Override
    public int getCount() {
      return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0: {
          return HistoryFragment.newInstance();
        }
        case 1: {
          return ArenaFragment.newInstance();
        }
        case 2: {
          return ClassStatsFragment.newInstance();
        }
        case 3: {
          return ArenaStatsFragment.newInstance();
        }
      }
      return null;
    }
  }

  @Override protected void onResume() {
    super.onResume();
    adapter.notifyDataSetChanged();
  }
}
