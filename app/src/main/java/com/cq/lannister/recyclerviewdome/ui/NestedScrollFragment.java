package com.cq.lannister.recyclerviewdome.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cq.lannister.recyclerviewdome.R;
import com.cq.lannister.recyclerviewdome.model.BrandModel;
import com.cq.lannister.recyclerviewdome.ui.adapter.BrandAdapter;
import com.cq.lannister.recyclerviewdome.util.DisplayHelper;
import com.cq.lannister.recyclerviewdome.widget.StickyNestedScrollLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * create by lym on 2018/7/25.
 */
public class NestedScrollFragment extends Fragment {

    Unbinder unbinder;

    BrandAdapter adapter;

    BrandModel mBrandModel;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BrandAdapter();
        mBrandModel = new BrandModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nested_scroll,container,false);
        unbinder = ButterKnife.bind(this, rootView);
        if (rootView instanceof StickyNestedScrollLayout){
            ((StickyNestedScrollLayout) rootView).setHeaderRetainHeight(DisplayHelper.dp2px(getActivity(),32));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setData(mBrandModel.brandList());
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
