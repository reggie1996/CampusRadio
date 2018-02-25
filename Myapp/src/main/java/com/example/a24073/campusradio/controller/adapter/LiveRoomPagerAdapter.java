package com.example.a24073.campusradio.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a24073.campusradio.controller.fragment.LiveRoomFragmentFour;
import com.example.a24073.campusradio.controller.fragment.LiveRoomFragmentOne;
import com.example.a24073.campusradio.controller.fragment.LiveRoomFragmentThree;
import com.example.a24073.campusradio.controller.fragment.LiveRoomFragmentTwo;

/**
 * Created by 24073 on 2017/7/18.
 */

public class LiveRoomPagerAdapter extends FragmentPagerAdapter {


    private Fragment fragmentone = null;
    private Fragment fragmenttwo = null;
    private Fragment fragmentfour = null;

    private String[] strs;

    public LiveRoomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public LiveRoomPagerAdapter(FragmentManager fm, String[] strs) {
        super(fm);
        this.strs = strs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                if(fragmentone  == null) {
                    fragmentone = new LiveRoomFragmentOne();
                    return fragmentone;
                }else
                    return fragmentone;
            case 1:
                if(fragmenttwo  == null) {
                    fragmenttwo = new LiveRoomFragmentTwo();
                    return fragmenttwo;
                }else
                    return fragmenttwo;
            case 2:
                if(fragmentfour  == null) {
                    fragmentfour = new LiveRoomFragmentFour();
                    return fragmentfour;
                }else
                    return fragmentfour;
            default:
                if(fragmentone  == null) {
                    fragmentone = new LiveRoomFragmentOne();
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
