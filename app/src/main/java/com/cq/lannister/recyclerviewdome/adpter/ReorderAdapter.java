package com.cq.lannister.recyclerviewdome.adpter;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.cq.lannister.recyclerviewdome.R;
import com.cq.lannister.recyclerviewdome.model.entity.Cheese;

/**
 * create by lym on 2019/10/28.
 */
public class ReorderAdapter extends AbsRecycleAdapter<Cheese>{

    OnItemLongClickListener mItemLongClickListener;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.reorder_cheese_item;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH vh = super.onCreateViewHolder(parent, viewType);
        vh.itemView.setOnLongClickListener(v -> {
            if (mItemLongClickListener != null) {
                mItemLongClickListener.onItemLongClick(vh);
                return true;
            }
            return false;
        });
        return vh;
    }

    @Override
    public void convert(VH holder, Cheese data, int position) {
        holder.setText(R.id.name,data.name);
        holder.setImageResid(R.id.image,data.image);
    }

    public void move(int from, int to){
        Cheese item = remove(from);
        addData(to, item);
        notifyItemMoved(from,to);
    }

    public void setItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        this.mItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(RecyclerView.ViewHolder viewHolder);
    }
}
