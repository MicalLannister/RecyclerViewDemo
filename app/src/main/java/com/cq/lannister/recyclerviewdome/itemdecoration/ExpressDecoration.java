package com.cq.lannister.recyclerviewdome.itemdecoration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.cq.lannister.recyclerviewdome.R;
import com.cq.lannister.recyclerviewdome.util.DisplayHelper;

/**
 * Created by lannister on 2017/11/9.
 */

public class ExpressDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    //ItemView左边的间距
    private int mOffsetLeft;
    //ItemView右边的间距
    private int mOffsetTop;
    //时间轴结点的半径
    private int mNodeRadius;
    //结点间隔
    private int mNodeInterval;
    private Bitmap mIcon;

    private int mIconWidth;

    public ExpressDecoration(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#7E7E7E"));

        mIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.location);
        mIconWidth = DisplayHelper.dp2px(context, 15);

        mOffsetLeft = DisplayHelper.dp2px(context, 48);
        mOffsetTop = DisplayHelper.dp2px(context, 24);
        mNodeRadius = DisplayHelper.dp2px(context, 4);
        mNodeInterval = DisplayHelper.dp2px(context, 4);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mOffsetTop;
        outRect.left = mOffsetLeft;
        outRect.bottom = mOffsetTop;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getLayoutManager().getItemCount();
        float dividerTop;
        float dividerLeft;
        float dividerBottom;
        float centerX;
        float centerY;
        float upLineTopX;
        float upLineTopY;
        float upLineBottomX;
        float upLineBottomY;
        float downLineTopX;
        float downLineTopY;
        float downLineBottomX;
        float downLineBottomY;
        for (int i = 0; i < childCount; i++) {

            View view = parent.getLayoutManager().findViewByPosition(i);

            if (view == null) {
                continue;
            }

            dividerTop = view.getTop() - mOffsetTop;
            dividerLeft = parent.getPaddingLeft();
            dividerBottom = view.getBottom() + mOffsetTop;

            centerX = dividerLeft + mOffsetLeft / 2;
            centerY = dividerTop + mOffsetTop + mNodeInterval * 2;

            upLineTopX = centerX;
            upLineTopY = dividerTop;
            upLineBottomX = centerX;
            upLineBottomY = centerY - mNodeRadius - mNodeInterval;

            //绘制上半部轴线
            if (i > 0) {
                c.drawLine(upLineTopX, upLineTopY, upLineBottomX, upLineBottomY, mPaint);
            }

            //绘制时间轴结点
            if (i == 0) {
                c.drawBitmap(mIcon, centerX - mIconWidth / 2, centerY - mIconWidth / 2, mPaint);
            } else {
                mPaint.setStyle(Paint.Style.STROKE);
                c.drawCircle(centerX, centerY, mNodeRadius, mPaint);
            }

            downLineTopX = centerX;
            downLineTopY = centerY + mNodeRadius + mNodeInterval;
            downLineBottomX = centerX;
            downLineBottomY = dividerBottom;

            //绘制上半部轴线
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            if (i == 0) {
                c.drawLine(downLineTopX, downLineTopY + mIconWidth / 2 + mNodeInterval, downLineBottomX, downLineBottomY, mPaint);
            } else if (i < childCount - 1) {
                c.drawLine(downLineTopX, downLineTopY, downLineBottomX, downLineBottomY, mPaint);
            }
        }
    }

}
