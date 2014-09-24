package com.trackobot.android.model.history.view;

import com.trackobot.android.model.history.Card;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class TurnActivityRow implements MatchDetailsView {

  private Card myCard;
  private Card opponentCard;

  public TurnActivityRow(Card myCard, Card opponentCard) {
    this.myCard = myCard;
    this.opponentCard = opponentCard;
  }

  public Card getMyCard() {
    return myCard;
  }

  public Card getOpponentCard() {
    return opponentCard;
  }

  public boolean has2Results() {
    if (myCard != null && opponentCard != null) {
      return true;
    }
    return false;
  }
}
