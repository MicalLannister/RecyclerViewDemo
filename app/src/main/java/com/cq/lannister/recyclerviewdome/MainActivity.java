package com.cq.lannister.recyclerviewdome;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.cq.lannister.recyclerviewdome.ui.NestedScrollFragment;
import com.cq.lannister.recyclerviewdome.ui.ReorderFragment;
import com.cq.lannister.recyclerviewdome.util.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),new ReorderFragment(),R.id.container);
    }

}
