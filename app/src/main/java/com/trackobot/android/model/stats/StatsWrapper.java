package com.trackobot.android.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lukasolsen on 22/09/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsWrapper {

  @JsonProperty("stats") Stats stats;

  public Stats getStats() {
    return stats;
  }
}
