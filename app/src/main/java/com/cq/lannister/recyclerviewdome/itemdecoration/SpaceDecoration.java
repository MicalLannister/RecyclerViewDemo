package com.cq.lannister.recyclerviewdome.itemdecoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.recyclerview.widget.RecyclerView;

/**
 * create by lym on 2019/10/28.
 */
public class SpaceDecoration extends RecyclerView.ItemDecoration {

    @Px
    private int startSpacing;

    @Px
    private int topSpacing;

    public SpaceDecoration(@Px int startSpacing, @Px int topSpacing) {
        this.startSpacing = startSpacing;
        this.topSpacing = topSpacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(startSpacing, topSpacing, startSpacing, topSpacing);
    }
}
