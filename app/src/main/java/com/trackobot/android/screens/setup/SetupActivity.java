package com.trackobot.android.screens.setup;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import butterknife.InjectView;
import com.dd.CircularProgressButton;
import com.trackobot.android.R;
import com.trackobot.android.model.user.UserUtils;
import com.trackobot.android.screens.main.MainActivity;
import com.trackobot.android.util.base.BaseActivity;
import com.wrapp.floatlabelededittext.FloatLabeledEditText;

/**
 * Created by lukasolsen on 21/09/14.
 */
public class SetupActivity extends BaseActivity implements SetupView {

  public static final int DELAY_MILLIS = 3000;
  public static final int PROGRESS_COMPLETE = 100;
  public static final int PROGRESS_LOADING = 50;
  public static final int PROGRESS_ERROR = -1;
  private SetupPresenter presenter;

  @InjectView(R.id.username_error) View usernameError;
  @InjectView(R.id.setup_activity_confirm) CircularProgressButton confirm;
  @InjectView(R.id.setup_activity_username) FloatLabeledEditText userName;
  @InjectView(R.id.setup_activity_accestoken) FloatLabeledEditText accesToken;
  @InjectView(R.id.setup_activity_link) TextView link;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    deactivateDrawer();
    getActionBar().hide();
    presenter = new SetupPresenter(this);
    userName.setText(UserUtils.getUserName(getBaseContext()));
    accesToken.setText(UserUtils.getAccesToken(getBaseContext()));
    link.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    link.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent browserIntent =
            new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.explanation_url)));
        startActivity(browserIntent);
      }
    });
    confirm.setIndeterminateProgressMode(true);
    confirm.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String userName = SetupActivity.this.userName.getTextString();
        String accesToken = SetupActivity.this.accesToken.getTextString();
        if (!userName.isEmpty() && !accesToken.isEmpty()) {
          presenter.checkAccess(userName, accesToken);
        } else {
          if (accesToken.isEmpty()) {
            String error = getString(R.string.error_token_empty);
            setErrorToEditText(SetupActivity.this.accesToken, error);
          }
          if (userName.isEmpty()) {
            String error = getString(R.string.error_username_empty);
            setErrorToEditText(SetupActivity.this.userName, error);
          }
        }
      }
    });
  }

  private void setErrorToEditText(final FloatLabeledEditText editText, String error) {
    editText.getEditText().setError(error);
    editText.getEditText().addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        editText.getEditText().setError(null);
        editText.getEditText().removeTextChangedListener(this);
      }

      @Override public void afterTextChanged(Editable s) {

      }
    });
  }

  @Override public int getLayoutId() {
    return R.layout.setup_activity;
  }

  @Override public void showError() {
    confirm.setProgress(PROGRESS_ERROR);
    userName.getEditText().setEnabled(true);
    usernameError.setVisibility(View.VISIBLE);
  }

  @Override public void showLoading() {
    confirm.setProgress(PROGRESS_LOADING);
    userName.getEditText().setEnabled(false);
  }

  @Override public void showContent() {
    confirm.setProgress(PROGRESS_COMPLETE);
    String userName = this.userName.getTextString();
    String accesToken = this.accesToken.getTextString();
    UserUtils.logUserIn(getApplicationContext(), userName, accesToken);
    Runnable finish = new Runnable() {
      @Override public void run() {
        Intent i = new Intent(SetupActivity.this, MainActivity.class);
        startActivity(i);
        finish();
      }
    };
    new Handler().postDelayed(finish, DELAY_MILLIS);
  }
}
