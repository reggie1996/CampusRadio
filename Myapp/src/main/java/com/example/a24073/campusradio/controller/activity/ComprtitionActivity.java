package com.example.a24073.campusradio.controller.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.brucetoo.gradienttabstrip.PagerSlidingTabStrip;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.adapter.ComepiPagerAdapter;

public class ComprtitionActivity extends FragmentActivity {

    private PagerSlidingTabStrip strip;
    private ViewPager pager;
    private String[] strs = new String[]{"比赛信息","上传我的作品"};
    ComepiPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprtition);

        strip = (PagerSlidingTabStrip)findViewById(R.id.strip_comepi);
        pager = (ViewPager)findViewById(R.id.pager_compi);
        adapter = new ComepiPagerAdapter(getSupportFragmentManager(),strs);
        setPager();
    }

    //-------------------------------------------设置下方的viewpager------------------------
    private void setPager(){
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
    }

}
