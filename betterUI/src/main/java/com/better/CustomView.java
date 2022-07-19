package com.better;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class CustomView extends View {

  private Paint paint;
  private RectF rect;
  private Path path;

  public CustomView(Context context) {
    super((context));
    paint = new Paint();
    paint.setAntiAlias(true);
    rect = new RectF();
    path = new Path();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    paint.setColor(Color.BLACK);
    rect.set(getLeft(), getTop(), getRight(), getBottom());
    canvas.drawRect(rect, paint);

    paint.setColor(Color.WHITE);
    rect.set(0, 0, 100, 100);
    canvas.drawArc(rect, 270, 360, true, paint);
  }
}
