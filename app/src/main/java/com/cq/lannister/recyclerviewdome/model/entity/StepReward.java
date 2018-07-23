package com.cq.lannister.recyclerviewdome.model.entity;

/**
 * create by lym on 2018/7/19.
 */
public class StepReward {
    public boolean isReached;
    public boolean isUsed;
    public String rewardDesc;
    public String receiveDesc;
    public String stepGoal;

    public StepReward(String rewardDesc,String receiveDesc, String stepGoal,boolean isReached, boolean isUsed) {
        this.rewardDesc = rewardDesc;
        this.receiveDesc = receiveDesc;
        this.stepGoal = stepGoal;
        this.isReached = isReached;
        this.isUsed = isUsed;
    }
}
