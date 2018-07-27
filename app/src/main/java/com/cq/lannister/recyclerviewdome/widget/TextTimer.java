package com.cq.lannister.recyclerviewdome.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by heng on 16-12-29.
 */

public class TextTimer extends View {
    private long mDuration = 0;

    private TextPaint mPaint;

//    private int mDay = 0;
//
//    private int mHour = 0;
//
//    private int mMinute = 0;
//
//    private int mSecond = 0;

    private int mTextSize;

    private int mTextSpace;

    private int mTextBackgroundColor;

    private int backgroundRadius;

    private int backgroundWidth;

    private int backgroundHeight;

    private int mColonColor;

    private boolean mDayHasBackground;

    private OnTimerOverListener timerOverListener;

    public void setTextSize(int textSize) {
        this.mTextSize = textSize;
    }

    public void setTextSpace(int textSpace) {
        this.mTextSpace = textSpace;
    }

    public void setTextBackgroundColor(int textBackgroundColor) {
        this.mTextBackgroundColor = textBackgroundColor;
    }

    public void setBackgroundWidth(int backgroundWidth) {
        this.backgroundWidth = backgroundWidth;
    }

    public void setBackgroundHeight(int backgroundHeight) {
        this.backgroundHeight = backgroundHeight;
    }

    public void setColonColor(int colonColor) {
        this.mColonColor = colonColor;
    }

    public void setDayHasBackground(boolean dayHasBackground) {
        this.mDayHasBackground = dayHasBackground;
    }

    public void setTimerOverListener(OnTimerOverListener timerOverListener) {
        this.timerOverListener = timerOverListener;
    }

    public TextTimer(Context context) {
        super(context);
        init(context);
    }

    public TextTimer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextTimer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mDensity = context.getResources().getDisplayMetrics().density;
        mTextSize = dip2px(14);//default
        mTextSpace = backgroundRadius = dip2px(5);//default
        mColonColor = mTextBackgroundColor = 0xFFFFD171;//default
        backgroundWidth = backgroundHeight = dip2px(25);//default
        mPaint = new TextPaint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        mTextHeight = fontMetrics.descent - fontMetrics.ascent;
    }

    private float mTextHeight;

    private float mDensity;

    private int dip2px(int dp) {
        return (int) (mDensity * dp + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            if (mDayHasBackground) {
                width = (int) (mPaint.measureText("天::")) + (mTextSpace * 6) + (backgroundWidth * 4);
            } else {
                TEMP_DATE td = getTEMP_DATE();
                width = (int) (mPaint.measureText(td.mDay + "天::")) + (mTextSpace * 5) + (backgroundWidth * 3);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            height = backgroundHeight;
        }
        setMeasuredDimension(width, height);
    }

    private final RectF rectF = new RectF();//time background rect

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TEMP_DATE td = getTEMP_DATE();
        final String dayStr = mDayHasBackground ? "天" : td.mDay + "天";
        final int height = getHeight();
        final float semWidth = mPaint.measureText(":");
        final float dayWidth = mPaint.measureText(dayStr);
        final float left = mDayHasBackground ? dayWidth + mTextSpace * 2 + backgroundWidth : dayWidth + mTextSpace;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTextBackgroundColor);
        if (mDayHasBackground) {
            rectF.set(0, 0, backgroundWidth, height);
            canvas.drawRoundRect(rectF, backgroundRadius, backgroundRadius, mPaint);
        }
        rectF.set(left, 0, left + backgroundWidth, height);
        canvas.drawRoundRect(rectF, backgroundRadius, backgroundRadius, mPaint);
        final float left2 = rectF.right + mTextSpace * 2 + semWidth;
        rectF.set(left2, 0, left2 + backgroundWidth, height);
        canvas.drawRoundRect(rectF, backgroundRadius, backgroundRadius, mPaint);
        final float left3 = rectF.right + mTextSpace * 2 + semWidth;
        rectF.set(left3, 0, left3 + backgroundWidth, height);
        canvas.drawRoundRect(rectF, backgroundRadius, backgroundRadius, mPaint);
        final float basePointY = height / 2f + mTextHeight / 3f;
        mPaint.setStyle(Paint.Style.STROKE);
        if (mDayHasBackground) {
            mPaint.setColor(mColonColor);
            canvas.drawText(dayStr, backgroundWidth + mTextSpace, basePointY, mPaint);
        } else {
            canvas.drawText(dayStr, 0, basePointY, mPaint);
        }
        canvas.drawText(":", left + backgroundWidth + mTextSpace, basePointY, mPaint);
        canvas.drawText(":", left + semWidth + backgroundWidth * 2 + mTextSpace * 3, basePointY, mPaint);
        mPaint.setColor(0xFFFFFFFF);
        final float halfBgWidth = backgroundWidth / 2f;

        int mHour = td.mHour;
        int mMinute = td.mMinute;
        int mSecond = td.mSecond;
        int mDay = td.mDay;

        final String hourStr = getTimeUnit(mHour);
        final String minuteStr = getTimeUnit(mMinute);
        final String secondStr = getTimeUnit(mSecond);
        final float hourWidth = mPaint.measureText(hourStr);
        final float minuteWidth = mPaint.measureText(minuteStr);
        final float secondWidth = mPaint.measureText(secondStr);
        final float hourStartX = left + halfBgWidth - hourWidth / 2f;
        if (mDayHasBackground) {
            final String dayString = String.valueOf(mDay);
            final float dayStartX = halfBgWidth - mPaint.measureText(dayString) / 2f;
            canvas.drawText(dayString, dayStartX, basePointY, mPaint);
        }
        canvas.drawText(hourStr, hourStartX, basePointY, mPaint);
        final float minuteStartX = left + semWidth + halfBgWidth + backgroundWidth + 2 * mTextSpace - minuteWidth / 2f;
        canvas.drawText(minuteStr, minuteStartX, basePointY, mPaint);
        final float secondStartX = left + semWidth * 2 + halfBgWidth + backgroundWidth * 2 + 4 * mTextSpace - secondWidth/ 2f;
        canvas.drawText(secondStr, secondStartX, basePointY, mPaint);

        postDelayed(runnable, 1000);
    }

    private String getTimeUnit(int size) {
        return size < 10 ? "0" + size : "" + size;
    }

//    private final long daySwap = 1000 * 60 * 60 * 24;
//    private final long hourSwap = 1000 * 60 * 60;
//    private final long minuteSwap = 1000 * 60;

    private void dhms() {
//        mDay = (int) (mDuration / daySwap);
//        mHour = (int) ((mDuration % daySwap) / hourSwap);
//        mMinute = (int) ((mDuration % hourSwap) / minuteSwap);
//        mSecond = (int) ((mDuration % minuteSwap) / 1000);
    }

    private SimpleDateFormat timeFormat(String regex) {
        SimpleDateFormat format = new SimpleDateFormat(regex, Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format;
    }

    private long expireTime = 0;
    public void setDuration(Date endDate) {
        try {
            expireTime = endDate.getTime();
            mDuration = endDate.getTime() - System.currentTimeMillis();
            dhms();
            invalidate();
        } catch (Exception e){}
    }

    public void setDuration(String endTime, String regex) {
        try {
            expireTime = timeFormat(regex).parse(endTime).getTime();
            mDuration =  expireTime - System.currentTimeMillis();
            dhms();
            invalidate();
        } catch (Exception e){}
    }

    public void setDuration(long endTime) {
        expireTime = endTime;
        mDuration = endTime - System.currentTimeMillis();
        dhms();
        invalidate();
    }

    public void setRealDuration(long duration) {
        expireTime = duration + System.currentTimeMillis();
        mDuration = duration;
        dhms();
        invalidate();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mDuration <= 0) {
                if (timerOverListener != null) {
                    timerOverListener.isOver();
                    removeCallbacks(this);
                }
                return;
            }
//            mDuration -= 1000;
//            if (mSecond == 0) {
//                mSecond = 59;
//                if (mMinute == 0) {
//                    mMinute = 59;
//                    if (mHour == 0) {
//                        mHour = 23;
//                        if (mDay > 0) {
//                            mDay -= 1;
//                        }
//                    } else {
//                        mHour -= 1;
//                    }
//                } else {
//                    mMinute -= 1;
//                }
//            } else {
//                mSecond -= 1;
//            }
            postInvalidate();
        }
    };

    public interface OnTimerOverListener {

        void isOver();
    }

    private static class TEMP_DATE{
        private int mDay = 0;
        private int mHour = 0;
        private int mMinute = 0;
        private int mSecond = 0;

        public TEMP_DATE(int mDay, int mHour, int mMinute, int mSecond) {
            this.mDay = mDay;
            this.mHour = mHour;
            this.mMinute = mMinute;
            this.mSecond = mSecond;
        }
    }
    private TEMP_DATE getTEMP_DATE(){
        int second = 1000;
        int minute = second * 60;
        int hour = minute * 60;
        int day = hour * 24;

        long curTime = System.currentTimeMillis();
        long diff = Math.abs(expireTime - curTime);
        Long freeDay = (diff / day);
        Long freeHour = (diff % day) / hour;
        Long freeMinute = ((diff % day) % hour) / minute;
        Long freeSeconds = (((diff % day) % hour) % minute) / second;
        return new TEMP_DATE(freeDay.intValue(),freeHour.intValue(),freeMinute.intValue(),freeSeconds.intValue());
    }
}
