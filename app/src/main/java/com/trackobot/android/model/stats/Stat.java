package com.trackobot.android.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trackobot.android.util.time.NumberUtil;

/**
 * Created by lukasolsen on 22/09/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stat {

  @JsonProperty int wins;
  @JsonProperty int losses;
  @JsonProperty int total;
  @JsonProperty int runs;

  public int getWins() {
    return wins;
  }

  public int getLosses() {
    return losses;
  }

  public int getTotal() {
    return total;
  }

  public int getRuns() {
    return runs;
  }

  public String getWinPercentage() {
    if (total > 0) {
      float percentage = (float) wins / (float) total * 100.0f;
      return NumberUtil.round(percentage, 2) + "%";
    }
    return "- %";
  }

  public float getWinNumberPercentage() {
    return (float) wins / (float) total;
  }

  public String getAverage() {
    if (runs > 0) {
      float percentage = (float) wins / (float) runs;
      return NumberUtil.round(percentage, 1) + "";
    }
    return "0";
  }
}
