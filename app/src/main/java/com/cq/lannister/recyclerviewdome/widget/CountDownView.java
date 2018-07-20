package com.cq.lannister.recyclerviewdome.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * create by lym on 2018/7/19.
 */
public class CountDownView extends View implements Runnable{

    private long mRemainingTime = 0L;

    public long getRemainingTime() {
        return mRemainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        mRemainingTime = remainingTime;
    }

    public CountDownView(Context context) {
        super(context);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        postDelayed(this,1000);
    }

    @Override
    public void run() {
        if (mRemainingTime <= 0){
            removeCallbacks(this);
            return;
        }
        postInvalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this);
    }
}
