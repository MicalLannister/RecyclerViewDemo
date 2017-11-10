package com.cq.lannister.recyclerviewdome.adpter;

import com.cq.lannister.recyclerviewdome.R;
import com.cq.lannister.recyclerviewdome.model.entity.Express;

/**
 * Created by lannister on 2017/11/9.
 */

public class ExpressAdapter extends AbsRecycleAdapter<Express> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_express;
    }

    @Override
    public void convert(VH holder, Express data, int position) {
        holder.setText(R.id.status,data.status);
        holder.setText(R.id.time,data.time);
    }
}
