package com.better;

import android.view.View;
import android.view.ViewGroup;

public class Functions {
  public static void removeFromParent(final View view) {
    final ViewGroup parent = (ViewGroup)view.getParent();
    if (parent != null)
    parent.removeView(view);
  }
}
