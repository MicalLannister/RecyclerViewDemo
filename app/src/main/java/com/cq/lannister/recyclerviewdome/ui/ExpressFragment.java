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
import com.cq.lannister.recyclerviewdome.adpter.ExpressAdapter;
import com.cq.lannister.recyclerviewdome.itemdecoration.ExpressDecoration;
import com.cq.lannister.recyclerviewdome.model.entity.Express;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lannister on 2017/11/10.
 */

public class ExpressFragment extends Fragment{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    ExpressAdapter adapter;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ExpressAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choice, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        bindView(rootView);
        return rootView;
    }

    private void bindView(View rootView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // TODO: 2017/11/10 样式的配置通过传入参数 把依赖放出来
        recyclerView.addItemDecoration(new ExpressDecoration(getActivity()));
        adapter.setData(generateData());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private List<Express> generateData() {
        List<Express> expresses = new ArrayList<>();
        expresses.add(new Express("2017-11-07 00:01:19", "[郑州市] 快件离开 [郑州中转]已发往[重庆中转]"));
        expresses.add(new Express("2017-11-07 00:01:19", "[郑州市] 快件离开 [郑州中转]已发往[重庆中转]"));
        expresses.add(new Express("2017-11-07 00:01:19", "[郑州市] 快件离开 [郑州中转]已发往[重庆中转]"));
        expresses.add(new Express("2017-11-07 00:01:19", "[郑州市] 快件离开 [郑州中转]已发往[重庆中转]"));
        expresses.add(new Express("2017-11-07 00:01:19", "[郑州市] 快件离开 [郑州中转]已发往[重庆中转]"));
        expresses.add(new Express("2017-11-07 00:01:19", "[郑州市] 快件离开 [郑州中转]已发往[重庆中转]"));
        expresses.add(new Express("2017-11-07 00:01:19", "[郑州市] 快件离开 [郑州中转]已发往[重庆中转]"));
        expresses.add(new Express("2017-11-07 00:01:19", "[郑州市] 快件离开 [郑州中转]已发往[重庆中转]"));
        expresses.add(new Express("2017-11-06 20:20:06", "[郑州市] [郑州金水一部]的郭昆已收件 电话:12312341234"));
        return expresses;
    }
}
