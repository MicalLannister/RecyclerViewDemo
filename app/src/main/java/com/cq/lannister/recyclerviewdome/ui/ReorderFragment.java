package com.cq.lannister.recyclerviewdome.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cq.lannister.recyclerviewdome.R;
import com.cq.lannister.recyclerviewdome.adpter.ReorderAdapter;
import com.cq.lannister.recyclerviewdome.itemdecoration.SpaceDecoration;
import com.cq.lannister.recyclerviewdome.model.entity.Cheese;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * create by lym on 2019/10/25.
 */
public class ReorderFragment extends Fragment {

    Unbinder unbinder;

    private Float pickUpElevation;

    private ReorderAdapter mAdapter;

    ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reorder,container,false);
        unbinder = ButterKnife.bind(this, rootView);
        int smallSpace = getResources().getDimensionPixelSize(R.dimen.spacing_small);
        pickUpElevation = getResources().getDimension(R.dimen.pick_up_elevation);
        mRecyclerView.addItemDecoration(new SpaceDecoration(smallSpace, smallSpace));
        mItemTouchHelper = new ItemTouchHelper(touchHelperCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mAdapter = new ReorderAdapter();
        mAdapter.setHasStableIds(true);
        mAdapter.setItemLongClickListener(viewHolder -> mItemTouchHelper.startDrag(viewHolder));
        mRecyclerView.setAdapter(mAdapter);
        show();
        return rootView;
    }

    private void toEashVisibleHolder(){
        int count = mRecyclerView.getChildCount();
        for (int i = 0; i < count; i++) {
            RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(i));
        }
    }

    private void show() {
        List<Cheese> list = new ArrayList<>(15);
        for (int i = 0; i < 15; i++) {
            list.add(Cheese.generator());
        }
        mAdapter.setData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private ItemTouchHelper.Callback touchHelperCallback = new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags((ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT), 0);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            mAdapter.move(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            //do nothing
        }

        @Override
        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
            if (viewHolder == null) {
                return;
            }
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                ViewCompat.animate(viewHolder.itemView).setDuration(150L).translationZ(pickUpElevation);
            }
        }

        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            ViewCompat.animate(viewHolder.itemView).setDuration(150L).translationZ(0f);
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }
    };
}
