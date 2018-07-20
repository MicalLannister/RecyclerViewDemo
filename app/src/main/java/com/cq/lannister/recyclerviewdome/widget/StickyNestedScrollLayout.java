package com.cq.lannister.recyclerviewdome.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * create by lym on 2018/7/20.
 */
public class StickyNestedScrollLayout extends LinearLayout implements NestedScrollingParent, NestedScrollingChild {

    private View mHeaderView;
    private View mBodyView;
    private int mMaxScrollHeight;
    private int mHeadrRetainHeight;

    public StickyNestedScrollLayout(Context context) {
        super(context);
    }

    public StickyNestedScrollLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StickyNestedScrollLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeaderView = getChildAt(0);
        mBodyView = getChildAt(1);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() > 1){
            throw new IllegalStateException("StickyNestedScrollLayout can host only two direct child");
        }
        super.addView(child, index, params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeaderView.measure(widthMeasureSpec,MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED));
        mMaxScrollHeight = mHeaderView.getMeasuredHeight() - mHeadrRetainHeight;
        //设置主体的高度：代码中设置match_parent
        if (mBodyView.getLayoutParams().height < getMeasuredHeight() - mHeadrRetainHeight){
            mBodyView.getLayoutParams().height = getMeasuredHeight() - mHeadrRetainHeight;
        }
        setMeasuredDimension(getMeasuredWidth(),mBodyView.getLayoutParams().height + mHeaderView.getMeasuredHeight());
    }



}
