package com.cq.lannister.recyclerviewdome.model;

import com.cq.lannister.recyclerviewdome.model.entity.StepReward;

import java.util.ArrayList;
import java.util.List;

/**
 * create by lym on 2018/7/23.
 */
public class RechargeRewardModel {
    public List<StepReward> getRewards(){
        List<StepReward> rewards = new ArrayList<>();
        rewards.add(new StepReward("10代金券","可领取","10",true,true));
        rewards.add(new StepReward("20代金券","可领取","20",true,true));
        rewards.add(new StepReward("30代金券","可领取","30",true,false));
        rewards.add(new StepReward("40代金券","未达到","40",false,false));
        rewards.add(new StepReward("50代金券","未达到","50",false,false));
        rewards.add(new StepReward("60代金券","未达到","60",false,false));
        rewards.add(new StepReward("70代金券","未达到","70",false,false));
        return rewards;
    }
}
