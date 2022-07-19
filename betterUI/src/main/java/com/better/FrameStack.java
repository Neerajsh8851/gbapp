package com.better;

import static java.lang.Math.max;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class FrameStack extends ViewGroup {
  private static final String TAG = "StackView";
  private final Stack<Frame> _stack = new Stack<>();
  private final Map<String, Frame> _routes = new HashMap<>();

  // gravity attributes
  private HGravity _hGravity = HGravity.CENTER;
  private VGravity _vGravity = VGravity.CENTER;

  // default animation
  private Animation enterAnimation;
  private Animation exitAnimation;


  public FrameStack(Context context) {
    super(context);
    enterAnimation = AnimationUtils.makeInAnimation(context, false);
    exitAnimation = AnimationUtils.makeOutAnimation(context, true);
  }

  public FrameStack(Context context, AttributeSet attributes) {
    super(context, attributes);
  }

  public static class LayoutParams extends MarginLayoutParams {

    public LayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
    }

    public LayoutParams(int width, int height) {
      super(width, height);
    }

    public LayoutParams(MarginLayoutParams source) {
      super(source);
    }

    public LayoutParams(ViewGroup.LayoutParams source) {
      super(source);
    }
  }

  public void setHGravity(HGravity g) {
    _hGravity = g;
  }

  public void setVGravity(VGravity g) {
    _vGravity = g;
  }

  public void setSize(int width, int height) {
    ViewGroup.LayoutParams lp = getLayoutParams();
    if (lp == null) {
      setLayoutParams(new LayoutParams(width, height));
      return;
    }
    lp.width = width;
    lp.height = height;
  }


  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int maxWidth = 0;
    int maxHeight = 0;
    int childState = 0;

    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
        maxWidth = max(maxWidth, child.getMeasuredWidth());
        maxHeight = max(maxHeight, child.getMeasuredHeight());
        childState = combineMeasuredStates(childState, child.getMeasuredState());
      }
    }
    maxHeight = max(maxHeight, getSuggestedMinimumHeight());
    maxWidth = max(maxWidth, getSuggestedMinimumWidth());

    int mW = resolveSizeAndState(maxWidth, widthMeasureSpec, childState);
    int mH = resolveSizeAndState(maxHeight, heightMeasureSpec, childState);
    setMeasuredDimension(mW, mH);
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    int x = 0, y = 0, cw, ch, pw, ph;
    pw = right - left;
    ph = bottom - top;
    for (int i = 0; i < getChildCount(); i++) {
      final View child = getChildAt(i);
      if (child.getVisibility() != View.GONE) {
        cw = child.getMeasuredWidth();
        ch = child.getMeasuredHeight();

        // calculation of x
        switch (_hGravity) {
          case START:
            x = left;
            break;
          case END:
            x = right - cw;
            break;
          case CENTER:
            x = left + (pw - cw) / 2;
        }

        // calculation of y
        switch (_vGravity) {
          case TOP:
            y = top;
            break;
          case BOTTOM:
            y = bottom - ch;
            break;
          case CENTER:
            y = top + (ph - ch) / 2;
        }

        child.layout(x, y, x + cw, y + ch);
      }
    }
  }

  public void store(String name, @NonNull Frame frame) {
    _routes.put(name, frame);
  }

  /****  Navigation functions ****/
  public void push(final Frame enteringFrame) {
    Log.d(TAG, "calling push");
    final Frame exitingFrame = getFrameAtTop();
    _stack.push(enteringFrame);

    final View exitingView = (exitingFrame == null) ? null : exitingFrame.onCreateView();
    final View enteringView = enteringFrame.onCreateView();
    removeAllViews();
    if (exitingView != null) {
      addView(exitingView);
      exitingView.startAnimation(exitAnimation);
    }
    addView(enteringView);
    enteringView.startAnimation(enterAnimation);

    exitAnimation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {
      }

      @Override
      public void onAnimationEnd(Animation animation) {
        Log.d(TAG, "remove exiting view");
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
          Functions.removeFromParent(exitingView);
        });
      }

      @Override
      public void onAnimationRepeat(Animation animation) {
      }
    });
  }

  public void push(String name) {
    final Frame frame = _routes.get(name);
    if (frame != null) {
      push(frame);
    }
  }

  public void pop() {
    Log.d(TAG, "calling pop");
    if (_stack.size() <= 1) return;
    final Frame exitingFrame = _stack.pop();
    final View exitingView = exitingFrame.onCreateView();
    final Frame frame = getFrameAtTop();
    View enteringView = null;
    if (frame != null) {
      enteringView = frame.onCreateView();
    }

    removeAllViews();
    if (enteringView != null) {
      addView(enteringView);
      enteringView.startAnimation(enterAnimation);
    }

    addView(exitingView);
    exitingView.startAnimation(exitAnimation);

    exitAnimation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {
      }

      @Override
      public void onAnimationEnd(Animation animation) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
          Functions.removeFromParent(exitingView);
        });
      }

      @Override
      public void onAnimationRepeat(Animation animation) {
      }
    });
  }

  /**
   * GET TOP VIEW
   *
   * @return TOP VIEW if any or null
   */
  private Frame getFrameAtTop() {
    if (_stack.empty()) {
      return null;
    }
    return _stack.peek();
  }

  /*** Functions to store the routes ****/


  public final void store(Route ...routes) {
    for (Route route : routes) {
      _routes.put(route.name, route.frame);
    }
  }

  @NonNull
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    // routes
    if (_routes.size() > 0) {
      str.append("routes {\n");
      for (String name : _routes.keySet()) {
        str.append(" ").append(name).append(": ").append(Objects.requireNonNull(_routes.get(name)).onCreateView().getClipBounds());
        str.append("\n");
      }
    }

    // stack
    str.append("stack size: ").append(_stack.size()).append("\n");
    return str.toString();
  }
}
