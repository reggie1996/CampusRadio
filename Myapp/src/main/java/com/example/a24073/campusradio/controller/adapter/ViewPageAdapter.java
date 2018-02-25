package com.example.a24073.campusradio.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a24073.campusradio.controller.fragment.MainFragmentFour;
import com.example.a24073.campusradio.controller.fragment.MainFragmentOne;
import com.example.a24073.campusradio.controller.fragment.MainFragmentThree;
import com.example.a24073.campusradio.controller.fragment.MainFragmentTwo;
import com.example.a24073.campusradio.controller.fragment.MeFragment;
import com.example.a24073.campusradio.controller.fragment.TestFragment;

/**
 * Created by 24073 on 2017/7/8.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {

    private Fragment fragmentone = null;
    private Fragment fragmenttwo = null;
    private Fragment fragmentthree = null;
    private Fragment fragmentfour = null;

    private String[] strs;

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPageAdapter(FragmentManager fm, String[] strs) {
        super(fm);
        this.strs = strs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                if(fragmentone  == null) {
                    fragmentone = new MainFragmentOne();
                    return fragmentone;
                }else
                    return fragmentone;
            case 1:
                if(fragmenttwo  == null) {
                    fragmenttwo = new MainFragmentTwo();
                    return fragmenttwo;
                }else
                    return fragmenttwo;
            case 2:
                if(fragmentthree  == null) {
                    fragmentthree = new MainFragmentThree();
                    return fragmentthree;
                }else
                    return fragmentthree;
            case 3:
                if(fragmentfour  == null) {
                    fragmentfour = new MainFragmentFour();
                    return fragmentfour;
                }else
                    return fragmentfour;
            default:
                if(fragmentone  == null) {
                    fragmentone = new MainFragmentOne();
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
