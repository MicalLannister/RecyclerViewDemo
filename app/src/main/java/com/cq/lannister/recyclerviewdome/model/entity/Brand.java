package com.cq.lannister.recyclerviewdome.model.entity;

import android.widget.Checkable;

/**
 * Created by lannister on 2017/11/3.
 */

public class Brand implements Checkable{

    public String mName;
    private boolean mChecked = false;

    public Brand(String mName) {
        this.mName = mName;
    }

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

}
