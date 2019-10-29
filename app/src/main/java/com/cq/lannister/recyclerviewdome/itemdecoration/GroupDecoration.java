package com.cq.lannister.recyclerviewdome.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cq.lannister.recyclerviewdome.util.DisplayHelper;

import java.util.List;

/**
 * create by lym on 2019/9/24.
 */
public class GroupDecoration extends RecyclerView.ItemDecoration  {

    private List<? extends IGroupItem> gridItems;

    private Paint mTitlePaint;
    private int mTitleColor;
    private int mTitleHeight;
    private int mTitleFontSize;
    private Rect mRect;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        IGroupItem item = gridItems.get(position);
        if (item == null || !item.isShow())
            return;
        if (position == 0) {
            outRect.set(0, mTitleHeight, 0, 0);
        } else {

        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int paddingLeft = parent.getPaddingLeft();
        int paddingRight = parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0;i<childCount;i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int pos = params.getViewLayoutPosition();
            IGroupItem item = gridItems.get(pos);
            if (item == null || !item.isShow())
                continue;
            if (i == 0) {
                drawTitle(c, paddingLeft, paddingRight, child,
                        (RecyclerView.LayoutParams) child.getLayoutParams(), pos,parent);
            }else {
                IGroupItem lastItem = gridItems.get(pos - 1);
                if (lastItem != null && !item.getTag().equals(lastItem.getTag())) {
                    drawTitle(c, paddingLeft, paddingRight, child,
                            (RecyclerView.LayoutParams) child.getLayoutParams(), pos,parent);
                }
            }
        }
    }

    private void drawTitle(Canvas canvas, int pl, int pr, View child, RecyclerView.LayoutParams params, int pos,RecyclerView parent) {
        IGroupItem item = gridItems.get(pos);
        String content = item.getTag();
        if (TextUtils.isEmpty(content))
            return;

        mTitlePaint.setColor(mTitleColor);
        mTitlePaint.setTextSize(mTitleFontSize);
        mTitlePaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTitlePaint.getTextBounds(content, 0, content.length(), mRect);
        float x = DisplayHelper.dp2px(parent.getContext(), 20);
        float y = child.getTop() - params.topMargin - (mTitleHeight - mRect.height()) / 2;
        canvas.drawText(content, x, y, mTitlePaint);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

}
