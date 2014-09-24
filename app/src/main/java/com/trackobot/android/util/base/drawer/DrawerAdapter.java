package com.trackobot.android.util.base.drawer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.model.user.UserUtils;
import com.trackobot.android.util.adapter.AbsModelBaseAdapter;
import com.trackobot.android.util.base.BaseViewHolder;
import com.trackobot.android.util.base.drawer.entries.DividerDrawerEntry;
import com.trackobot.android.util.base.drawer.entries.DrawerEntry;
import com.trackobot.android.util.base.drawer.entries.HeadlineDrawerEntry;
import com.trackobot.android.util.base.drawer.entries.SettingsDrawerEntry;
import com.trackobot.android.util.base.drawer.entries.ShareDrawerEntry;
import com.trackobot.android.util.base.drawer.entries.SpaceDrawerEntry;
import com.trackobot.android.util.base.drawer.entries.UserDrawerEntry;

/**
 * Created by lukasolsen on 22/09/14.
 */
public class DrawerAdapter extends AbsModelBaseAdapter<DrawerEntry> {

  private static final int VIEWTYPE_NOT_FOUND = -1;

  private static final int VIEWTYPE_USER = 0;
  private static final int VIEWTYPE_SETTING = 1;
  private static final int VIEWTYPE_DIVIDER = 2;
  private static final int VIEWTYPE_HEADLINE = 3;
  private static final int VIEWTYPE_SPACE = 4;

  private static final int VIEWTYPE_COUNT = 5;

  class DrawerSettingsViewHolder extends BaseViewHolder {

    @InjectView(R.id.drawer_entry_settings_text) TextView settingsText;

    public DrawerSettingsViewHolder(View view) {
      super(view);
    }
  }

  class DrawerHeadlineViewHolder extends BaseViewHolder {

    @InjectView(R.id.drawer_entry_headline_text) TextView headline;

    public DrawerHeadlineViewHolder(View view) {
      super(view);
    }
  }

  class DrawerUserViewHolder extends BaseViewHolder {

    @InjectView(R.id.drawer_entry_user_name) TextView username;

    public DrawerUserViewHolder(View view) {
      super(view);
    }
  }

  public DrawerAdapter(Context context) {
    super(context);
    setItems(DrawerEntries.getDrawerEntries(context));
  }

  @Override public View newView(int type, ViewGroup parent) {
    View view = null;
    switch (type) {
      case VIEWTYPE_SETTING: {
        view = inflater.inflate(R.layout.drawer_entry_settings, parent, false);
        view.setTag(new DrawerSettingsViewHolder(view));
        break;
      }
      case VIEWTYPE_USER: {
        view = inflater.inflate(R.layout.drawer_entry_user, parent, false);
        view.setTag(new DrawerUserViewHolder(view));
        break;
      }
      case VIEWTYPE_DIVIDER: {
        view = inflater.inflate(R.layout.drawer_entry_divider, parent, false);
        break;
      }
      case VIEWTYPE_HEADLINE: {
        view = inflater.inflate(R.layout.drawer_entry_headline, parent, false);
        view.setTag(new DrawerHeadlineViewHolder(view));
        break;
      }
      case VIEWTYPE_SPACE: {
        view = inflater.inflate(R.layout.drawer_entry_spacer, parent, false);
        break;
      }
    }
    return view;
  }

  @Override public void bindView(int position, int type, View view) {
    switch (type) {
      case VIEWTYPE_SETTING: {
        SettingsDrawerEntry entry = (SettingsDrawerEntry) getItem(position);
        DrawerSettingsViewHolder holder = (DrawerSettingsViewHolder) view.getTag();
        Intent i = entry.getIntent();
        if (i != null) {
          addLinking(entry, holder, i);
        }
        holder.settingsText.setText(entry.getText());
        break;
      }
      case VIEWTYPE_HEADLINE: {
        HeadlineDrawerEntry entry = (HeadlineDrawerEntry) getItem(position);
        DrawerHeadlineViewHolder holder = (DrawerHeadlineViewHolder) view.getTag();
        holder.headline.setText(entry.getHeadline());
        break;
      }
      case VIEWTYPE_USER: {
        DrawerUserViewHolder holder = (DrawerUserViewHolder) view.getTag();
        holder.username.setText(UserUtils.getUserName(context));
        break;
      }
    }
  }

  private void addLinking(SettingsDrawerEntry entry, DrawerSettingsViewHolder holder, Intent i) {
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    if (entry instanceof ShareDrawerEntry) {
      i.putExtra(Intent.EXTRA_TEXT, DrawerEntries.playstoreUrl);
      i.setType("text/plain");
      String title = context.getResources().getString(R.string.chooser_title);
      final Intent chooser = Intent.createChooser(i, title);
      chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      if (i.resolveActivity(context.getPackageManager()) != null) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            context.startActivity(chooser);
          }
        });
      }
    } else {
      final Intent tempIntent = i;
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          context.startActivity(tempIntent);
        }
      });
    }
  }

  public static int getViewtypeCount() {
    return VIEWTYPE_COUNT;
  }

  @Override public int getItemViewType(int position) {
    DrawerEntry entry = getItem(position);
    if (entry instanceof UserDrawerEntry) {
      return VIEWTYPE_USER;
    }
    if (entry instanceof SettingsDrawerEntry) {
      return VIEWTYPE_SETTING;
    }
    if (entry instanceof DividerDrawerEntry) {
      return VIEWTYPE_DIVIDER;
    }
    if (entry instanceof HeadlineDrawerEntry) {
      return VIEWTYPE_HEADLINE;
    }
    if (entry instanceof SpaceDrawerEntry) {
      return VIEWTYPE_SPACE;
    }
    return VIEWTYPE_NOT_FOUND;
  }
}
