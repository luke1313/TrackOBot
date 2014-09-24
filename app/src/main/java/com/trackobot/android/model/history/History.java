package com.trackobot.android.model.history;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trackobot.android.model.history.view.MatchDetailsView;
import com.trackobot.android.model.history.view.ResultRow;
import com.trackobot.android.model.history.view.TurnActivityRow;
import com.trackobot.android.model.history.view.TurnRow;
import com.trackobot.android.util.time.TimeUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasolsen on 21/09/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class History implements Parcelable {

  @JsonProperty String id;
  @JsonProperty String mode;
  @JsonProperty String hero;
  @JsonProperty String opponent;
  @JsonProperty boolean coin;
  @JsonProperty String result;
  @JsonProperty long duration;
  @JsonProperty String added;
  @JsonProperty("card_history") List<CardHistory> cardHistory;

  public History(Parcel in) {
    this();
    id = in.readString();
    mode = in.readString();
    hero = in.readString();
    opponent = in.readString();
    coin = in.readByte() != 0;
    result = in.readString();
    duration = in.readLong();
    added = in.readString();
    in.readTypedList(cardHistory, CardHistory.CREATOR);
  }

  public History() {
    cardHistory = new ArrayList<CardHistory>();
  }

  public String getId() {
    return id;
  }

  public String getAdded() {
    return added;
  }

  public String getMode() {
    return mode;
  }

  public String getHero() {
    return hero;
  }

  public String getOpponent() {
    return opponent;
  }

  public boolean isCoin() {
    return coin;
  }

  public String getResult() {
    return result;
  }

  public long getDuration() {
    return duration;
  }

  public String getDurationString() {
    return TimeUtils.convertSecondsToString(getDuration());
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(mode);
    dest.writeString(hero);
    dest.writeString(opponent);
    dest.writeByte((byte) (coin ? 1 : 0));
    dest.writeString(result);
    dest.writeLong(duration);
    dest.writeString(added);
    dest.writeTypedList(cardHistory);
  }

  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public History createFromParcel(Parcel in) {
      return new History(in);
    }

    public History[] newArray(int size) {
      return new History[size];
    }
  };

  public List<MatchDetailsView> createList() {
    List<MatchDetailsView> detailsViewList = new ArrayList<MatchDetailsView>();
    int lastTurn = -1;
    for (int i = 0; i < cardHistory.size() - 1; i++) {
      CardHistory history = cardHistory.get(i);
      int currTurn = history.getTurn();
      if (currTurn > lastTurn) {
        detailsViewList.add(new TurnRow(currTurn));
      }
      lastTurn = currTurn;
      CardHistory nextHistory = cardHistory.get(i + 1);
      TurnActivityRow row = buildTurnActivity(history, nextHistory);
      if (row != null) {
        detailsViewList.add(row);
        if (row.has2Results()) {
          i = i + 1;
        }
      }
    }
    detailsViewList.add(new ResultRow(result));
    return detailsViewList;
  }

  private TurnActivityRow buildTurnActivity(CardHistory firstHistory, CardHistory secondHistory) {
    TurnActivityRow row = null;
    if (secondHistory != null) {
      int turnOfFirst = firstHistory.getTurn();
      int turnOfSecond = secondHistory.getTurn();
      if (turnOfFirst != turnOfSecond) {
        if (firstHistory.getPlayer().equalsIgnoreCase("me")) {
          row = new TurnActivityRow(firstHistory.getCard(), null);
        } else {
          row = new TurnActivityRow(null, firstHistory.getCard());
        }
      } else {
        if (!firstHistory.getPlayer().equalsIgnoreCase(secondHistory.getPlayer())) {
          if (firstHistory.getPlayer().equalsIgnoreCase("me")) {
            row = new TurnActivityRow(firstHistory.getCard(), secondHistory.getCard());
          } else {
            row = new TurnActivityRow(secondHistory.getCard(), firstHistory.getCard());
          }
        }
      }
    } else {
      if (firstHistory.getPlayer().equalsIgnoreCase("me")) {
        row = new TurnActivityRow(firstHistory.getCard(), null);
      } else {
        row = new TurnActivityRow(null, firstHistory.getCard());
      }
    }
    return row;
  }
}
