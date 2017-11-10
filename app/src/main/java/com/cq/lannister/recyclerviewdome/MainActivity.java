package com.cq.lannister.recyclerviewdome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.cq.lannister.recyclerviewdome.ui.ChoiceFragment;
import com.cq.lannister.recyclerviewdome.ui.ExpressFragment;
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
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),new ExpressFragment(),R.id.container);
    }
}
