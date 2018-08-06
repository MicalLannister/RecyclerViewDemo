package com.cq.lannister.recyclerviewdome.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cq.lannister.recyclerviewdome.R;
import com.cq.lannister.recyclerviewdome.util.DisplayHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * create by lym on 2018/7/30.
 */
public class DemoFragment extends Fragment {

    @BindView(R.id.textView)
    TextView mTextView;

    Unbinder mbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demo,container,false);
        mbinder = ButterKnife.bind(this,rootView);
        mTextView.setText(generateCurrentChargeDesc());
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mbinder.unbind();
    }

    private SpannableString generateCurrentChargeDesc() {
        String prefix = "累计充值奖励：";
        String middle = "\n已充值";
        String resultString = prefix + middle + "2.5" + "元";
        SpannableString spannable = new SpannableString(resultString);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#AEAEAE")),0,prefix.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new AbsoluteSizeSpan(DisplayHelper.dp2px(getContext(), 16)), prefix.length() + middle.length(), resultString.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannable;
    }
}
