package com.trackobot.android.model.arena;

import android.util.Log;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trackobot.android.model.history.History;
import com.trackobot.android.util.time.TimeUtils;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Created by lukasolsen on 22/09/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Arena {

  @JsonProperty String id;
  @JsonProperty String hero;
  @JsonProperty String wins;
  @JsonProperty String losses;
  @JsonProperty("results") List<History> results;

  public String getId() {
    return id;
  }

  public String getHero() {
    return hero;
  }

  public String getWins() {
    return wins;
  }

  public String getLosses() {
    return losses;
  }

  public List<History> getResults() {
    return results;
  }

  public String buildTitle() {
    return hero + ", " + wins + " wins";
  }

  public String getResultString() {
    return wins + " Wins, " + losses + " Losses";
  }

  public DateTime getDateString() {
    if (results != null) {
      if (results.get(0) != null) {
        History firstResult = results.get(0);
        String addedString = firstResult.getAdded();
        DateTime currentLowest = TimeUtils.createDateFromString(addedString);
        for (History history : results) {
          DateTime newDate = TimeUtils.createDateFromString(history.getAdded());
          if (newDate.isAfter(currentLowest)) {
            currentLowest = newDate;
          }
        }
        return currentLowest;
      }
    }
    return null;
  }
}
