package com.cq.lannister.recyclerviewdome.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cq.lannister.recyclerviewdome.model.entity.StepReward;

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

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(20,20,20,20);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
