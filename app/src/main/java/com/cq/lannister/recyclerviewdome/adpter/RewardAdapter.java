package com.cq.lannister.recyclerviewdome.adpter;

import com.cq.lannister.recyclerviewdome.R;
import com.cq.lannister.recyclerviewdome.model.entity.StepReward;

/**
 * create by lym on 2018/7/23.
 */
public class RewardAdapter extends AbsRecycleAdapter<StepReward>{
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_reward;
    }

    @Override
    public void convert(VH holder, StepReward data, int position) {
        holder.setText(R.id.receiveDesc,data.receiveDesc);
        holder.setText(R.id.rewardDesc,data.rewardDesc);
    }

}
