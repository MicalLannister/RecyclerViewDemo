package com.cq.lannister.recyclerviewdome.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cq.lannister.recyclerviewdome.R;
import com.cq.lannister.recyclerviewdome.adpter.RewardAdapter;
import com.cq.lannister.recyclerviewdome.itemdecoration.ExpressDecoration;
import com.cq.lannister.recyclerviewdome.itemdecoration.RewardDecoration;
import com.cq.lannister.recyclerviewdome.model.RechargeRewardModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * create by lym on 2018/7/19.
 */
public class StepRewardFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.addItemDecoration(new RewardDecoration(getActivity()));
        adapter.setData(model.getRewards());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
