package com.trackobot.android.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trackobot.android.model.stats.classes.Druid;
import com.trackobot.android.model.stats.classes.Hunter;
import com.trackobot.android.model.stats.classes.Mage;
import com.trackobot.android.model.stats.classes.Paladin;
import com.trackobot.android.model.stats.classes.Priest;
import com.trackobot.android.model.stats.classes.Rogue;
import com.trackobot.android.model.stats.classes.Shaman;
import com.trackobot.android.model.stats.classes.Warlock;
import com.trackobot.android.model.stats.classes.Warrior;

/**
 * Created by lukasolsen on 22/09/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassStats {

  @JsonProperty("Druid") Druid druid;
  @JsonProperty("Priest") Priest priest;
  @JsonProperty("Shaman") Shaman shaman;
  @JsonProperty("Hunter") Hunter hunter;
  @JsonProperty("Mage") Mage mage;
  @JsonProperty("Paladin") Paladin paladin;
  @JsonProperty("Warlock") Warlock warlock;
  @JsonProperty("Rogue") Rogue rogue;
  @JsonProperty("Warrior") Warrior warrior;

  public Druid getDruid() {
    return druid;
  }

  public Priest getPriest() {
    return priest;
  }

  public Shaman getShaman() {
    return shaman;
  }

  public Hunter getHunter() {
    return hunter;
  }

  public Mage getMage() {
    return mage;
  }

  public Paladin getPaladin() {
    return paladin;
  }

  public Warlock getWarlock() {
    return warlock;
  }

  public Rogue getRogue() {
    return rogue;
  }

  public Warrior getWarrior() {
    return warrior;
  }
}
