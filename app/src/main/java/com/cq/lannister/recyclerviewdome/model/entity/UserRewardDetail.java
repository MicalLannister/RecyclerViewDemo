package com.cq.lannister.recyclerviewdome.model.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * create by lym on 2018/7/20.
 */
public class UserRewardDetail implements Serializable {
    private double rechageNum;
    private int limitRechargeMoney;
    private String icon;
    private String desc;
    private List<RechargeRewardConfig> rewardConfigs;

    public double getRechageNum() {
        return rechageNum;
    }

    public void setRechageNum(double rechageNum) {
        this.rechageNum = rechageNum;
    }

    public int getLimitRechargeMoney() {
        return limitRechargeMoney;
    }

    public void setLimitRechargeMoney(int limitRechargeMoney) {
        this.limitRechargeMoney = limitRechargeMoney;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<RechargeRewardConfig> getRewardConfigs() {
        if (rewardConfigs == null) {
            return Collections.emptyList();
        }
        for (RechargeRewardConfig rewardConfig : rewardConfigs) {
            rewardConfig.setFinished(judgeIsFinish(rewardConfig.getNum()));
            rewardConfig.setReceived(rewardConfig.getStatus() > 1);
        }
        rewardConfigs.add(0, generateRewardConfig());
        return rewardConfigs;
    }

    public void setRewardConfigs(List<RechargeRewardConfig> rewardConfigs) {
        this.rewardConfigs = rewardConfigs;
    }

    private RechargeRewardConfig generateRewardConfig() {
        RechargeRewardConfig firstItem = new RechargeRewardConfig();
        firstItem.setCid("");
        firstItem.setRewardDetails(desc);
        firstItem.setIcon(icon);
        firstItem.setFinished(judgeIsFinish(limitRechargeMoney));
        firstItem.setReceived(false);
        return firstItem;
    }

    private boolean judgeIsFinish(int levelRechargeMoney) {
        return rechageNum >= levelRechargeMoney;
    }

//    "rewardConfigs":[{"cid":"Reward_2","icon":"https://d.ireadercity.com/images/welfare/readtimes1.png","rewardDetails":"50代金券","num":50,"status":1},{"cid":"Reward_6","icon":"https://d.ireadercity.com/images/welfare/readtimes1.png","rewardDetails":"100代金券","num":100,"status":1},{"cid":"Reward_15","icon":"https://d.ireadercity.com/images/welfare/readtimes1.png","rewardDetails":"3天VIP","num":3,"status":1},{"cid":"Reward_30","icon":"https://d.ireadercity.com/images/welfare/readtimes1.png","rewardDetails":"200代金券","num":200,"status":1},{"cid":"Reward_60","icon":"https://d.ireadercity.com/images/welfare/readtimes1.png","rewardDetails":"500代金券","num":500,"status":1},{"cid":"Reward_100","icon":"https://d.ireadercity.com/images/welfare/readtimes1.png","rewardDetails":"7天全场免费","num":7,"status":1}]

}
