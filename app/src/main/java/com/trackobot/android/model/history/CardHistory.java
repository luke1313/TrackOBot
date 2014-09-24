package com.trackobot.android.model.history;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class CardHistory implements Parcelable {

  @JsonProperty("player") String player;
  @JsonProperty("turn") int turn;
  @JsonProperty("card") Card card;

  public CardHistory() {

  }

  public CardHistory(Parcel in) {
    player = in.readString();
    turn = in.readInt();
    card = in.readParcelable(Card.class.getClassLoader());
  }

  public String getPlayer() {
    return player;
  }

  public int getTurn() {
    return turn;
  }

  public Card getCard() {
    return card;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(player);
    dest.writeInt(turn);
    dest.writeParcelable(card, flags);
  }

  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public CardHistory createFromParcel(Parcel in) {
      return new CardHistory(in);
    }

    public CardHistory[] newArray(int size) {
      return new CardHistory[size];
    }
  };
}
