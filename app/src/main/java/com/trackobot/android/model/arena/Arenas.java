package com.trackobot.android.model.arena;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Created by lukasolsen on 22/09/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Arenas {

  @JsonProperty("arena") List<Arena> arenas;

  public List<Arena> getArenas() {
    return arenas;
  }
}
