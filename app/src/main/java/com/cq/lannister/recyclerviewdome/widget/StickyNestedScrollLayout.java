package com.cq.lannister.recyclerviewdome.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * create by lym on 2018/7/20.
 */
public class StickyNestedScrollLayout extends LinearLayout implements NestedScrollingParent {

    private View mHeaderView;
    private View mBodyView;
    private int mMaxScrollHeight;
    private ValueAnimator mScrollAnimator;

    public void setHeaderRetainHeight(int headerRetainHeight) {
        mHeaderRetainHeight = headerRetainHeight;
    }

    private int mHeaderRetainHeight;

    private NestedScrollingChildHelper mNestedScrollingChildHelper;
    private NestedScrollingParentHelper mNestedScrollingParentHelper;

    public StickyNestedScrollLayout(Context context) {
        this(context, null);
    }

    public StickyNestedScrollLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyNestedScrollLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeaderView = getChildAt(0);
        mBodyView = getChildAt(1);
        mHeaderView.setFocusable(true);
        mHeaderView.setClickable(true);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() > 1) {
            throw new IllegalStateException("StickyNestedScrollLayout can host only two direct child");
        }
        super.addView(child, index, params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeaderView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        mMaxScrollHeight = mHeaderView.getMeasuredHeight() - mHeaderRetainHeight;
        //设置主体的高度：代码中设置match_parent
        if (mBodyView.getLayoutParams().height < getMeasuredHeight() - mHeaderRetainHeight) {
            mBodyView.getLayoutParams().height = getMeasuredHeight() - mHeaderRetainHeight;
        }
        setMeasuredDimension(getMeasuredWidth(), mBodyView.getLayoutParams().height + mHeaderView.getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    private boolean canScroll(View target, int dy) {
        boolean hiddenTop = dy > 0 && getScrollY() < mMaxScrollHeight;
        boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);
        return hiddenTop || showTop;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (canScroll(target, dy)) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (ViewCompat.canScrollVertically(target, -1)) {
            return false;
        }
        if (mScrollAnimator != null && mScrollAnimator.isStarted()) {
            mScrollAnimator.cancel();
        }
        if (velocityX == 0 && velocityY != 0) {
            if (velocityY > 0) {
                scrollTo(0, mMaxScrollHeight);
            } else {
                scrollTo(0, 0);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    private void smoothToSticky(float velocityY) {
        float velY = velocityY / 1000f;
        if (Math.abs(velY) < 1f) {
            return;
        }
        final float fromY = getScaleY();
        final float toY = fromY + velY * 300L;
        mScrollAnimator = ValueAnimator.ofFloat(1f)
                .setDuration(300L);
        mScrollAnimator.setInterpolator(new DecelerateInterpolator(2.0f));
        mScrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                float curY = (toY - fromY) * fraction + fromY;
                if (curY < 0) {
                    scrollTo(0, 0);
                } else if (curY > mMaxScrollHeight) {
                    scrollTo(0, mMaxScrollHeight);
                } else {
                    scrollTo(0, (int) curY);
                }
            }
        });
        mScrollAnimator.start();
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);
    }


    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

}
