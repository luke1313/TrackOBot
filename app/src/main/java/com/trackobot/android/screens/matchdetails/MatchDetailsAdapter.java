package com.trackobot.android.screens.matchdetails;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import com.trackobot.android.R;
import com.trackobot.android.model.history.Card;
import com.trackobot.android.model.history.view.MatchDetailsView;
import com.trackobot.android.model.history.view.ResultRow;
import com.trackobot.android.model.history.view.TurnActivityRow;
import com.trackobot.android.model.history.view.TurnRow;
import com.trackobot.android.util.adapter.AbsModelBaseAdapter;
import com.trackobot.android.util.base.BaseViewHolder;

/**
 * Created by lukasolsen on 23/09/14.
 */
public class MatchDetailsAdapter extends AbsModelBaseAdapter<MatchDetailsView> {

  private static final int VIEWTYPE_NOT_FOUND = -1;

  private static final int VIEWTYPE_TURN_ROW = 0;
  private static final int VIEWTYPE_TURN_ACTIVITY_ROW = 1;
  private static final int VIEWTYPE_RESULT = 2;

  private static final int VIEWTYPE_COUNT = 3;

  class TurnRowViewHolder extends BaseViewHolder {

    @InjectView(R.id.matchdetails_turn_row_text) TextView turnText;

    public TurnRowViewHolder(View view) {
      super(view);
    }
  }

  class TurnActivityViewHolder extends BaseViewHolder {

    @InjectView(R.id.matchdetails_turnactivity_row_card1_name) TextView player1CardName;
    @InjectView(R.id.matchdetails_turnactivity_row_card1_mana) TextView player1CardMana;
    @InjectView(R.id.matchdetails_turnactivity_row_card1_container) View player1CardContainer;
    @InjectView(R.id.matchdetails_turnactivity_row_card2_name) TextView player2CardName;
    @InjectView(R.id.matchdetails_turnactivity_row_card2_mana) TextView player2CardMana;
    @InjectView(R.id.matchdetails_turnactivity_row_card2_container) View player2CardContainer;

    public TurnActivityViewHolder(View view) {
      super(view);
    }
  }

  class ResultViewHolder extends BaseViewHolder {

    @InjectView(R.id.matchdetails_result_row_result) TextView result;

    public ResultViewHolder(View view) {
      super(view);
    }
  }

  public MatchDetailsAdapter(Context context) {
    super(context);
  }

  @Override public View newView(int type, ViewGroup parent) {
    View view = null;
    switch (type) {
      case VIEWTYPE_TURN_ROW: {
        view = inflater.inflate(R.layout.matchdetails_turn_row, parent, false);
        view.setTag(new TurnRowViewHolder(view));
        break;
      }
      case VIEWTYPE_TURN_ACTIVITY_ROW: {
        view = inflater.inflate(R.layout.matchdetails_turnactivity_row, parent, false);
        view.setTag(new TurnActivityViewHolder(view));
        break;
      }
      case VIEWTYPE_RESULT: {
        view = inflater.inflate(R.layout.matchdetails_result_row, parent, false);
        view.setTag(new ResultViewHolder(view));
        break;
      }
    }
    return view;
  }

  @Override public void bindView(int position, int type, View view) {
    switch (type) {
      case VIEWTYPE_TURN_ROW: {
        TurnRow row = (TurnRow) getItem(position);
        TurnRowViewHolder holder = (TurnRowViewHolder) view.getTag();
        holder.turnText.setText(row.getTurn() + "");
        break;
      }
      case VIEWTYPE_RESULT: {
        ResultViewHolder holder = (ResultViewHolder) view.getTag();
        ResultRow row = (ResultRow) getItem(position);
        holder.result.setText(row.getResultText());
        break;
      }
      case VIEWTYPE_TURN_ACTIVITY_ROW: {
        TurnActivityViewHolder holder = (TurnActivityViewHolder) view.getTag();
        TurnActivityRow row = (TurnActivityRow) getItem(position);
        Card myCard = row.getMyCard();
        Card opponentCard = row.getOpponentCard();
        if (myCard != null) {
          holder.player1CardContainer.setVisibility(View.VISIBLE);
          holder.player1CardMana.setText(myCard.getMana() + "");
          holder.player1CardName.setText(myCard.getName() + "");
        } else {
          holder.player1CardContainer.setVisibility(View.INVISIBLE);
        }
        if (opponentCard != null) {
          holder.player2CardContainer.setVisibility(View.VISIBLE);
          holder.player2CardMana.setText(opponentCard.getMana() + "");
          holder.player2CardName.setText(opponentCard.getName() + "");
        } else {
          holder.player2CardContainer.setVisibility(View.INVISIBLE);
        }
        break;
      }
    }
  }

  @Override public int getItemViewType(int position) {
    MatchDetailsView item = getItem(position);
    if (item instanceof TurnRow) {
      return VIEWTYPE_TURN_ROW;
    }
    if (item instanceof TurnActivityRow) {
      return VIEWTYPE_TURN_ACTIVITY_ROW;
    }
    if (item instanceof ResultRow) {
      return VIEWTYPE_RESULT;
    }
    return VIEWTYPE_NOT_FOUND;
  }

  @Override public int getViewTypeCount() {
    return VIEWTYPE_COUNT;
  }
}
