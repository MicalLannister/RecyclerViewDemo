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
import com.cq.lannister.recyclerviewdome.adpter.AbsRecycleAdapter;
import com.cq.lannister.recyclerviewdome.model.BrandModel;
import com.cq.lannister.recyclerviewdome.ui.adapter.BrandAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lannister on 2017/11/3.
 */

public class ChoiceFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    BrandModel mModel;
    Unbinder unbinder;
    BrandAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new BrandModel();
        adapter = new BrandAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choice, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        bindView(rootView);
        return rootView;
    }

    void bindView(View rootView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        //设置adapter的单选模式
        adapter.setChoiceMode(AbsRecycleAdapter.CHOICE_MODE_SINGLE);
        adapter.setData(mModel.brandList());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
