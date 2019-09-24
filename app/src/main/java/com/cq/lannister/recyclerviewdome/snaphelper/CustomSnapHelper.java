package com.cq.lannister.recyclerviewdome.snaphelper;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * create by lym on 2018/7/24.
 */
public class CustomSnapHelper  extends LinearSnapHelper {
    private OrientationHelper mHorizontalHelper;
    private boolean isLast = false;

    public CustomSnapHelper(boolean isLast) {
        this.isLast = isLast;
    }

    @Override
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        int[] out = new int[2];
        //判断支持水平滚动，修改水平方向的位置，是修改的out[0]的值
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }
        return out;
    }
    private int distanceToStart(View targetView, OrientationHelper helper) {
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
    }
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return findStartView(layoutManager, getHorizontalHelper(layoutManager));
    }
    private View findStartView(RecyclerView.LayoutManager layoutManager,
                               OrientationHelper helper) {
        if (layoutManager instanceof LinearLayoutManager) {
            int firstChild = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            int lastChild = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            if (firstChild == RecyclerView.NO_POSITION) {
                return null;
            }
            //这行的作用是如果是最后一个，翻到最后一条，解决显示不全的问题
            if (lastChild == layoutManager.getItemCount() - 1 && isLast) {
                return layoutManager.findViewByPosition(lastChild);
            }
            View child = layoutManager.findViewByPosition(firstChild);
            //获取偏左显示的Item
            if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2
                    && helper.getDecoratedEnd(child) > 0) {
                return child;
            } else {
                return layoutManager.findViewByPosition(firstChild + 1);
            }
        }
        return super.findSnapView(layoutManager);
    }
    private OrientationHelper getHorizontalHelper(
            RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}
