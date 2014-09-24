package com.trackobot.android.model.history.view;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class TurnRow implements MatchDetailsView {

  private int turn;

  public TurnRow(int turn) {
    this.turn = turn;
  }

  public int getTurn() {
    return turn;
  }
}
