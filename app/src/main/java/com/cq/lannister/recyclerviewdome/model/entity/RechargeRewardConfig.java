package com.cq.lannister.recyclerviewdome.model.entity;

/**
 * create by lym on 2018/7/20.
 */
public class RechargeRewardConfig {
    private String cid;
    private String rewardDetails;
    private String icon;
    //0:未完成\1:未获取\2:已获取\-1:Unknown
    private int status;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private boolean isFinished;

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }

    private boolean isReceived;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRewardDetails() {
        return rewardDetails;
    }

    public void setRewardDetails(String rewardDetails) {
        this.rewardDetails = rewardDetails;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
