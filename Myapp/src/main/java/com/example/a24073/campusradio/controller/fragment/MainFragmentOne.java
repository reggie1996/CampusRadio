package com.example.a24073.campusradio.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.activity.LiveRoomActivity;
import com.example.a24073.campusradio.controller.activity.MyLiveRoomActivity;
import com.example.a24073.campusradio.controller.activity.RadioTypeActivity;
import com.example.a24073.campusradio.controller.activity.RankingListActivity;
import com.example.a24073.campusradio.controller.adapter.LiveRoomAdapter;
import com.example.a24073.campusradio.model.bean.LiveRoom;
import com.example.a24073.campusradio.utils.MyGridView;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24073 on 2017/7/9.
 */

public class MainFragmentOne extends Fragment {

    MyGridView gv_schoolliveroom;
    MyGridView gv_hotliveroom;
    List<LiveRoom> schoolLiveRooms;
    List<LiveRoom> hotLiveRooms;
    View rankinglist;
    View localschool;
    TextView more1;
    TextView more2;
    android.support.design.widget.FloatingActionButton fab_myliveroom;

    private View slideView;
    private SlideUp slideUp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mainone, null);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        more1 = (TextView) view.findViewById(R.id.more1);
        more2 = (TextView) view.findViewById(R.id.more2);
        more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), RadioTypeActivity.class);//从一个activity跳转到另一个activity  
                intent.putExtra("str", "校园电台");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                getContext().startActivity(intent);
            }
        });
        more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), RadioTypeActivity.class);//从一个activity跳转到另一个activity  
                intent.putExtra("str", "私人电台");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                getContext().startActivity(intent);
            }
        });

        gv_schoolliveroom = (MyGridView) view.findViewById(R.id.gv_schoolliveroom);
        gv_hotliveroom = (MyGridView) view.findViewById(R.id.gv_hotliveroom);
        rankinglist = view.findViewById(R.id.rankinglist);
        rankinglist.setOnClickListener(new View.OnClickListener() {             //进入排行榜界面
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RankingListActivity.class);
                startActivity(intent);
            }
        });
        localschool = view.findViewById(R.id.mainone_localschool);
        localschool.setOnClickListener(new View.OnClickListener() {             //进入本校直播间界面
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), LiveRoomActivity.class);//从一个activity跳转到另一个activity  
                intent.putExtra("str", "0");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                startActivity(intent);
            }
        });

        schoolLiveRooms = new ArrayList<>();
        hotLiveRooms = new ArrayList<>();

        schoolLiveRooms.add(new LiveRoom("http://baike.haixiangjiaoyu.com/uploads/201410/1414387520MrkRAwUm_s.jpg","耶鲁校园电台","耶鲁",231,true));
        schoolLiveRooms.add(new LiveRoom("http://e-images.juwaistatic.com/2016/08/Harvard_University.jpg","哈佛校园电台","哈佛",219,true));
        schoolLiveRooms.add(new LiveRoom("http://www.logo11.cn/uploads/allimg/141107/1_141107100510_1.jpg","芝加哥大学电台","Chicago",177,false));
        schoolLiveRooms.add(new LiveRoom("http://d.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=f285556c8c13632715b8ca37a4bf8cda/34fae6cd7b899e51ece9f3a444a7d933c8950d1f.jpg","霍格沃茨电台","邓布利多",119,true));

        hotLiveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/36NtMMRofAn1C4iGMsvysfpBeMRxg578HreyrgZtvKk!/b/dD0BAAAAAAAA","YASUSU的烘培间","YASUSU",432,true));
        hotLiveRooms.add(new LiveRoom("http://a2.qpic.cn/psb?/V121cQJl2s5so0/bJN9l6qwz2oRk9VjWnfxfw.tc3PkysumLAsjpj7Mrwg!/b/dGwBAAAAAAAA","千的日常播报","长岛没有秋刀鱼",387,true));
        hotLiveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/jUj6QUhdMEMu4J22vi9EPbl0WVVBgnE.PzSFph2hRiE!/b/dD0BAAAAAAAA","炸鸡块电台","北斋家的大虾",112,true));
        hotLiveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/KQjJpJVezS3WsGQF*7XUCmz2W7qSsOZPGneXiroSTMU!/b/dD0BAAAAAAAA","天啦噜的广播电台","拉拉酱",122,false));

        gv_schoolliveroom.setAdapter(new LiveRoomAdapter(schoolLiveRooms,getActivity()));
        gv_hotliveroom.setAdapter(new LiveRoomAdapter(hotLiveRooms,getActivity()));

        gv_schoolliveroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent();
                intent.setClass(getContext(), LiveRoomActivity.class);//从一个activity跳转到另一个activity  
                if(schoolLiveRooms.get(position).isIflive()){
                    intent.putExtra("str", "0");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                }else {
                    intent.putExtra("str", "3");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                }
                startActivity(intent);
            }
        });

        gv_hotliveroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(getContext(), LiveRoomActivity.class);//从一个activity跳转到另一个activity  
                if(hotLiveRooms.get(position).isIflive()){
                    intent.putExtra("str", "1");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                }else {
                    intent.putExtra("str", "3");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                }
                startActivity(intent);
            }
        });

        fab_myliveroom = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.fab_myliveroom);
        fab_myliveroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(),MyLiveRoomActivity.class).putExtra("str","1"));
            }
        });
        fab_myliveroom.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                slideUp.show();
                return true;
            }
        });

        slideView = view.findViewById(R.id.popupslide);
        slideUp = new SlideUpBuilder(slideView)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {

                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility == View.GONE){

                        }
                    }
                })
                .withStartGravity(Gravity.TOP)
                .withLoggingEnabled(true)
                .withStartState(SlideUp.State.HIDDEN)
                .build();


    }

}
