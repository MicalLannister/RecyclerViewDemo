package com.cq.lannister.recyclerviewdome.ui.adapter;

import com.cq.lannister.recyclerviewdome.R;
import com.cq.lannister.recyclerviewdome.adpter.AbsRecycleAdapter;
import com.cq.lannister.recyclerviewdome.model.entity.Brand;

/**
 * Created by lannister on 2017/11/3.
 */

public class BrandAdapter extends AbsRecycleAdapter<Brand> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_size;
    }

    @Override
    public void convert(VH holder, Brand data, int position) {
        holder.setText(R.id.brandName,data.mName);
        holder.setImageResid(R.id.selectedIcon, data.isChecked() ? R.mipmap.checkbox_checked : R.mipmap.checkbox_uncheck);
    }
}
