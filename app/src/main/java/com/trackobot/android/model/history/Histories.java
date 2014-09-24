package com.trackobot.android.model.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Created by lukasolsen on 21/09/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Histories {

  @JsonProperty("history") List<History> historyList;

  public List<History> getHistoryList() {
    return historyList;
  }
}
