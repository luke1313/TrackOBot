package com.trackobot.android.util.base;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.ButterKnife;
import com.trackobot.android.R;
import com.trackobot.android.util.BarUtils;
import com.trackobot.android.util.base.drawer.DrawerAdapter;
import java.util.List;

/**
 * Created by lukasolsen on 22/09/14.
 */
public abstract class BaseActivity extends FragmentActivity {

  public interface HomeClickedListener {
    public void onHomeClicked();
  }

  private FrameLayout content;
  private View spacer;
  private DrawerLayout drawer;
  private ListView drawerList;
  private DrawerAdapter drawerAdapter;
  private HomeClickedListener homeClickedListener;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.base_activity);
    ImageView home = (ImageView) findViewById(android.R.id.home);
    home.setPadding(getResources().getDimensionPixelSize(R.dimen.homePaddingLeft), 0,
        getResources().getDimensionPixelSize(R.dimen.homePaddingRight), 0);
    drawer = (DrawerLayout) findViewById(R.id.base_activity_drawer);
    drawerList = (ListView) findViewById(R.id.base_activity_drawer_list);
    drawerAdapter = new DrawerAdapter(getBaseContext());
    drawerList.setAdapter(drawerAdapter);
    spacer = findViewById(R.id.base_activity_spacer);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      int statusBarHeight = BarUtils.getStatusBarHeight(getBaseContext());
      ;
      ViewGroup.LayoutParams spacerParams = spacer.getLayoutParams();
      spacerParams.height += statusBarHeight;
      spacer.setLayoutParams(spacerParams);
      DrawerLayout.LayoutParams listParams =
          (DrawerLayout.LayoutParams) drawerList.getLayoutParams();
      listParams.topMargin += statusBarHeight;
      drawerList.setLayoutParams(listParams);
    }
    content = (FrameLayout) findViewById(R.id.base_activity_content);
    View view = LayoutInflater.from(getBaseContext()).inflate(getLayoutId(), content, false);
    content.addView(view);
    ButterKnife.inject(this);
  }

  protected void deactivateDrawer() {
    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
  }

  protected void toggleDrawer() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      drawer.openDrawer(GravityCompat.START);
    }
  }

  public abstract int getLayoutId();

  protected void setHomeAsUpEnabled(HomeClickedListener listener) {
    getActionBar().setDisplayHomeAsUpEnabled(true);
    this.homeClickedListener = listener;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int itemId = item.getItemId();
    if (itemId == android.R.id.home) {
      homeClickedListener.onHomeClicked();
    }
    return super.onOptionsItemSelected(item);
  }
}
