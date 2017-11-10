package com.cq.lannister.recyclerviewdome.model.entity;

/**
 * Created by lannister on 2017/11/9.
 */

public class Express {


    public Express(String time, String status) {
        this.time = time;
        this.status = status;
    }

    /**
     * time : 2017-11-06 20:29:49
     * status : [郑州市] 快件离开 [郑州金水一部]已发往[重庆]
     */

    public String time;
    public String status;
}
