package com.trackobot.android.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trackobot.android.model.stats.view.DividerRow;
import com.trackobot.android.model.stats.view.GraphRow;
import com.trackobot.android.model.stats.view.HeadlineRow;
import com.trackobot.android.model.stats.view.OverallChartRow;
import com.trackobot.android.model.stats.view.SpaceRow;
import com.trackobot.android.model.stats.view.StatRow;
import com.trackobot.android.model.stats.view.StatisticsView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasolsen on 22/09/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {

  @JsonProperty("overall") OverallStats overallStats;
  @JsonProperty("as_class") AsClass asClass;
  @JsonProperty("vs_class") VsClass vsClass;
  @JsonProperty("as_hero") AsHero asHero;
  @JsonProperty("count_by_wins") List<Integer> countByWins;

  public OverallStats getOverallStats() {
    return overallStats;
  }

  public AsClass getAsClass() {
    return asClass;
  }

  public VsClass getVsClass() {
    return vsClass;
  }

  public List<StatisticsView> getList() {
    List<StatisticsView> list = new ArrayList<StatisticsView>();
    if (overallStats != null) {
      list.add(new OverallChartRow(overallStats));
    }
    list.addAll(addClassStats("as", asClass));
    list.addAll(addClassStats("vs", vsClass));
    list.addAll(addClassStats(null, asHero));
    if (countByWins != null && countByWins.size() > 0) {
      list.add(new GraphRow());
    }
    return list;
  }

  private List<StatisticsView> addClassStats(String headline, ClassStats stats) {
    List<StatisticsView> list = new ArrayList<StatisticsView>();
    if (stats != null) {
      list.add(new SpaceRow());
      list.add(new StatRow("Druid", stats.getDruid(), headline));
      list.add(new DividerRow());
      list.add(new StatRow("Priest", stats.getPriest(), headline));
      list.add(new DividerRow());
      list.add(new StatRow("Shaman", stats.getShaman(), headline));
      list.add(new DividerRow());
      list.add(new StatRow("Hunter", stats.getHunter(), headline));
      list.add(new DividerRow());
      list.add(new StatRow("Mage", stats.getMage(), headline));
      list.add(new DividerRow());
      list.add(new StatRow("Paladin", stats.getPaladin(), headline));
      list.add(new DividerRow());
      list.add(new StatRow("Warlock", stats.getWarlock(), headline));
      list.add(new DividerRow());
      list.add(new StatRow("Rogue", stats.getRogue(), headline));
      list.add(new DividerRow());
      list.add(new StatRow("Warrior", stats.getWarrior(), headline));
    }
    return list;
  }
}
