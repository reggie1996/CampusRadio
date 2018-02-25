package com.example.a24073.campusradio.controller.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.brucetoo.gradienttabstrip.PagerActivity;
import com.brucetoo.gradienttabstrip.PagerSlidingTabStrip;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.base.BaseFragment;
import com.example.a24073.campusradio.controller.adapter.ViewPageAdapter;

/**
 * Created by 24073 on 2017/6/3.
 * 主界面（校园广播）
 */

public class MainFragment extends BaseFragment{

    private View view;

    private PagerSlidingTabStrip strip;
    private ViewPager pager;
    private ViewPageAdapter adapter;
    private String[] strs = new String[]{"电台","分类","学习","班级"};

    @Override
    protected View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_main,null);

        strip = (PagerSlidingTabStrip) view.findViewById(R.id.strip_main);
        pager = (ViewPager) view.findViewById(R.id.pager_main);
        adapter = new ViewPageAdapter(getActivity().getSupportFragmentManager(),strs);

        //tab text color
        strip.setTextColor(Color.parseColor("#8a8a8a"));
        //tab chose text color
        strip.setTabChoseTextColor(Color.parseColor("#00645c"));
        //tab text size
        strip.setTextSize(13);
        //tab chose text size
        strip.setTabChoseTextSize(17);
        //indicator color
        strip.setIndicatorColor(Color.parseColor("#00645c"));
        //indicator height
        strip.setIndicatorHeight(4);
        //underline height
        strip.setUnderlineHeight(1);
        //expand?
        strip.setShouldExpand(true);
        //divider between tab
        strip.setDividerColor(android.R.color.transparent);

        pager.setAdapter(adapter);
        strip.setViewPager(pager);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
