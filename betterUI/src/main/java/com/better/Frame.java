package com.better;

import android.content.Context;
import android.view.View;

public abstract class Frame {
  private final Context mCtx;

  public Frame(Context context) {
    this.mCtx = context;
  }
  protected abstract View onCreateView();

  public Context getContext() {
    return mCtx;
  }
}
