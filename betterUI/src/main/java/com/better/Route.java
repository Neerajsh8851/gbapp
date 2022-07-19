package com.better;

import android.view.View;

public class Route {
  public String name;
  public Frame frame;

  public Route(String name, Frame f) {
    this.name = name;
    this.frame = f;
  }

  public String getName() {
    return name;
  }

  public Frame getView() {
    return frame;
  }

  public static Route route(String name, Frame f) {
    return new Route(name, f);
  }
}
