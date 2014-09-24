package com.trackobot.android.model.history;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class Card implements Parcelable {

  @JsonProperty("id") String id;
  @JsonProperty("name") String name;
  @JsonProperty("mana") int mana;

  public Card() {

  }

  public Card(Parcel in) {
    id = in.readString();
    name = in.readString();
    mana = in.readInt();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getMana() {
    return mana;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(name);
    dest.writeInt(mana);
  }

  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public Card createFromParcel(Parcel in) {
      return new Card(in);
    }

    public Card[] newArray(int size) {
      return new Card[size];
    }
  };
}
