package com.trackobot.android.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.trackobot.android.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lukasolsen on 24/09/14.
 */
public class ClassIcon extends com.trackobot.android.util.CircleImageView {

  private static final String CLASS_DRUID = "druid";
  private static final String CLASS_MAGE = "mage";
  private static final String CLASS_PRIEST = "priest";
  private static final String CLASS_WARLOCK = "warlock";
  private static final String CLASS_WARRIOR = "warrior";
  private static final String CLASS_PALADIN = "paladin";
  private static final String CLASS_SHAMAN = "shaman";
  private static final String CLASS_ROGUE = "rogue";
  private static final String CLASS_HUNTER = "hunter";

  public ClassIcon(Context context) {
    super(context);
  }

  public ClassIcon(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ClassIcon(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public void setClassName(String className) {
    if (className != null) {
      if (className.equalsIgnoreCase(CLASS_DRUID)) {
        setImageResource(R.drawable.ic_druid);
        setBorderColor(getContext().getResources().getColor(R.color.color_druid));
      } else if (className.equalsIgnoreCase(CLASS_MAGE)) {
        setImageResource(R.drawable.ic_mage);
        setBorderColor(getContext().getResources().getColor(R.color.color_mage));
      } else if (className.equalsIgnoreCase(CLASS_PRIEST)) {
        setImageResource(R.drawable.ic_priest);
        setBorderColor(getContext().getResources().getColor(R.color.color_priest));
      } else if (className.equalsIgnoreCase(CLASS_WARLOCK)) {
        setImageResource(R.drawable.ic_warlock);
        setBorderColor(getContext().getResources().getColor(R.color.color_warlock));
      } else if (className.equalsIgnoreCase(CLASS_WARRIOR)) {
        setImageResource(R.drawable.ic_warrior);
        setBorderColor(getContext().getResources().getColor(R.color.color_warrior));
      } else if (className.equalsIgnoreCase(CLASS_PALADIN)) {
        setImageResource(R.drawable.ic_paladin);
        setBorderColor(getContext().getResources().getColor(R.color.color_paladin));
      } else if (className.equalsIgnoreCase(CLASS_SHAMAN)) {
        setImageResource(R.drawable.ic_shaman);
        setBorderColor(getContext().getResources().getColor(R.color.color_shaman));
      } else if (className.equalsIgnoreCase(CLASS_ROGUE)) {
        setImageResource(R.drawable.ic_rogue);
        setBorderColor(getContext().getResources().getColor(R.color.color_rogue));
      } else if (className.equalsIgnoreCase(CLASS_HUNTER)) {
        setImageResource(R.drawable.ic_hunter);
        setBorderColor(getContext().getResources().getColor(R.color.color_hunter));
      }
    }
  }
}
