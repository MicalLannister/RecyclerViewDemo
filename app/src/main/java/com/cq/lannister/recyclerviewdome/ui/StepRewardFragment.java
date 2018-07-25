package com.cq.lannister.recyclerviewdome.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cq.lannister.recyclerviewdome.R;
import com.cq.lannister.recyclerviewdome.adpter.RewardAdapter;
import com.cq.lannister.recyclerviewdome.itemdecoration.RewardDecoration;
import com.cq.lannister.recyclerviewdome.model.RechargeRewardModel;
import com.cq.lannister.recyclerviewdome.snaphelper.CustomSnapHelper;
import com.cq.lannister.recyclerviewdome.util.DisplayHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * create by lym on 2018/7/19.
 */
public class StepRewardFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.buttonPanel)
    Button mButton;

    RewardAdapter adapter;

    RechargeRewardModel model;

    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new RechargeRewardModel();
        adapter = new RewardAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        bindView(rootView);
        return rootView;
    }

    private void bindView(View rootView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new RewardDecoration(getActivity()));
        adapter.setData(model.getRewards());
        recyclerView.setAdapter(adapter);
        CustomSnapHelper snapHelper = new CustomSnapHelper(false);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @OnClick(R.id.buttonPanel)
    void onClick() {
        int p = 1;
        int mOffsetLeft = DisplayHelper.dp2px(getActivity(), 24);
        int childCount = recyclerView.getChildCount();
        int distance = recyclerView.getPaddingLeft();
        p = p > childCount ? childCount : p;
        for (int i = 0; i < p; i++) {
            View view = recyclerView.getChildAt(i);
            distance += view.getWidth() + mOffsetLeft*2;
            Log.e("mical","mearsure:->"+(view.getWidth() + mOffsetLeft*2));
        }
        recyclerView.smoothScrollBy(distance, 0);

        OrientationHelper mVerticalHelper = OrientationHelper.createVerticalHelper(recyclerView.getLayoutManager());
        int start = mVerticalHelper.getDecoratedStart(recyclerView.getLayoutManager().findViewByPosition(1));
        Log.e("mical","start:"+start);
        Log.e("mical","total:"+mVerticalHelper.getDecoratedMeasurement(recyclerView.getChildAt(1)));
//        recyclerView.smoothScrollBy(start,0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
