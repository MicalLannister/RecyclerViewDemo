package com.cq.lannister.recyclerviewdome.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.cq.lannister.recyclerviewdome.model.entity.StepReward;
import com.cq.lannister.recyclerviewdome.util.DisplayHelper;

import java.util.List;

/**
 * create by lym on 2018/7/23.
 */
public class RewardDecoration extends RecyclerView.ItemDecoration {

    private List<StepReward> mRewards;
    private Paint mPaint;
    private int mOffsetLeft;
    private int mOffsetTop;
    private int mNodeRadius;

    public RewardDecoration(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mOffsetLeft = DisplayHelper.dp2px(context, 24);
        mOffsetTop = DisplayHelper.dp2px(context, 48);
        mNodeRadius = DisplayHelper.dp2px(context, 12);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(mOffsetLeft, mOffsetTop, mOffsetLeft, 0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        /*最开始用的是parent.getChild(int index)导致屏幕可显示的的第一次item总是当做全部的第一个导致异常的显示
        * 用findViewByPosition(int position)就是相当于adapter里面的位置*/

        int childCount = parent.getLayoutManager().getItemCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getLayoutManager().findViewByPosition(i);
            if (view == null) {
                continue;
            }
            mPaint.setStyle(Paint.Style.STROKE);
            float dividerTop = parent.getPaddingTop();
            float dividerLeft = view.getLeft() - mOffsetLeft;
            float dividerRight = view.getRight() + mOffsetLeft;
            float centerX = (view.getLeft() + view.getRight()) >> 1;
            float centerY = (dividerTop + view.getTop()) / 2;
            if (i > 0) {
                c.drawLine(dividerLeft, centerY, dividerRight, centerY, mPaint);
            }

        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
