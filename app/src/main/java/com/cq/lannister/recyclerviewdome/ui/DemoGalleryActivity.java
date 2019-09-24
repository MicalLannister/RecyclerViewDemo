package com.cq.lannister.recyclerviewdome.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cq.lannister.recyclerviewdome.R;

/**
 * create by lym on 2018/7/25.
 */
public class DemoGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_demo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
