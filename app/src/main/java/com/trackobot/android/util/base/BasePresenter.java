package com.trackobot.android.util.base;

import android.util.Log;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.trackobot.android.Config;
import java.io.IOException;
import java.lang.ref.WeakReference;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by lukasolsen on 21/09/14.
 */
public class BasePresenter<V extends BaseView> {

  WeakReference<V> view;

  public BasePresenter(V view) {
    this.view = new WeakReference<V>(view);
  }

  private void loading() {
    if (isViewAttached()) {
      getView().showLoading();
    }
  }

  private void success() {
    if (isViewAttached()) {
      getView().showContent();
    }
  }

  private void error() {
    getView().showError();
  }

  protected boolean isViewAttached() {
    if (view.get() != null) {
      return true;
    }
    return false;
  }

  protected V getView() {
    return view.get();
  }

  public abstract class ResponseHandler<D extends Object> extends JsonHttpResponseHandler {

    private Class<D> classType;

    public ResponseHandler(Class<D> classType) {
      this.classType = classType;
    }

    public abstract void onSucces(D d);

    private void parseObject(String jsonString) {
      logJsonString(jsonString);
      ObjectMapper mapper = new ObjectMapper();
      try {
        D d = mapper.readValue(jsonString, classType);
        if (d != null) {
          success();
          onSucces(d);
        } else {
          error();
        }
      } catch (IOException e) {
        e.printStackTrace();
        error();
      }
    }

    protected void logJsonString(String jsonString) {
      if (Config.HTTP_LOGGING) {
        Log.d(getClass().getSimpleName(), jsonString);
      }
    }

    @Override public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
      parseObject(response.toString());
    }

    @Override public void onSuccess(int statusCode, Header[] headers, String responseString) {
      parseObject(responseString);
    }

    @Override public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
      parseObject(response.toString());
    }

    @Override public void onStart() {
      super.onStart();
      loading();
    }

    @Override public void onFailure(int statusCode, Header[] headers, String responseString,
        Throwable throwable) {
      Log.e(getClass().getSimpleName(), responseString);
      error();
    }

    @Override public void onFailure(int statusCode, Header[] headers, Throwable throwable,
        JSONObject errorResponse) {
      Log.e(getClass().getSimpleName(), throwable.getMessage());
      error();
    }

    @Override public void onFailure(int statusCode, Header[] headers, Throwable throwable,
        JSONArray errorResponse) {
      Log.e(getClass().getSimpleName(), throwable.getMessage());
      error();
    }
  }
}
