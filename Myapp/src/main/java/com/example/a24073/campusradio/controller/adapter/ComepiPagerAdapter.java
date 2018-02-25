package com.example.a24073.campusradio.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a24073.campusradio.controller.fragment.ComepiFragmentOne;
import com.example.a24073.campusradio.controller.fragment.ComepiFragmentTwo;

/**
 * Created by 24073 on 2017/8/1.
 */

public class ComepiPagerAdapter extends FragmentPagerAdapter {


    private Fragment fragmentone = null;
    private Fragment fragmenttwo = null;

    private String[] strs;

    public ComepiPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ComepiPagerAdapter(FragmentManager fm, String[] strs) {
        super(fm);
        this.strs = strs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                if(fragmentone  == null) {
                    fragmentone = new ComepiFragmentOne();
                    return fragmentone;
                }else
                    return fragmentone;
            case 1:
                if(fragmenttwo  == null) {
                    fragmenttwo = new ComepiFragmentTwo();
                    return fragmenttwo;
                }else
                    return fragmenttwo;
            default:
                if(fragmentone  == null) {
                    fragmentone = new ComepiFragmentOne();
                    return fragmentone;
                }else
                    return fragmentone;
        }
    }

    @Override
    public int getCount() {
        return strs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strs[position];
    }
}
