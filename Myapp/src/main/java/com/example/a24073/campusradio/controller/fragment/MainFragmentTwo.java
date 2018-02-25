package com.example.a24073.campusradio.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.activity.LiveRoomActivity;
import com.example.a24073.campusradio.controller.adapter.LiveRoomAdapter;
import com.example.a24073.campusradio.controller.adapter.TextTagsAdapter;
import com.example.a24073.campusradio.model.bean.LiveRoom;
import com.example.a24073.campusradio.utils.MyGridView;
import com.moxun.tagcloudlib.view.TagCloudView;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24073 on 2017/7/9.
 */

public class MainFragmentTwo extends Fragment {

    private TagCloudView tagCloudView;
    private TextTagsAdapter textTagsAdapter;

    private MyGridView gv_liveroomsuggest;
    private List<LiveRoom> liveRooms;

    private ImageView search;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maintwo, null);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        tagCloudView = (TagCloudView) view.findViewById(R.id.tagcloudview);
        textTagsAdapter = new TextTagsAdapter(getContext(),new String[20]);
        tagCloudView.setAdapter(textTagsAdapter);
        super.onViewCreated(view, savedInstanceState);

        gv_liveroomsuggest = (MyGridView) view.findViewById(R.id.gv_liveroomsuggest);
        liveRooms = new ArrayList<>();
        liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/36NtMMRofAn1C4iGMsvysfpBeMRxg578HreyrgZtvKk!/b/dD0BAAAAAAAA","YASUSU的烘培间","YASUSU",432,true));
        liveRooms.add(new LiveRoom("http://baike.haixiangjiaoyu.com/uploads/201410/1414387520MrkRAwUm_s.jpg","耶鲁校园电台","耶鲁",231,true));

        gv_liveroomsuggest.setAdapter(new LiveRoomAdapter(liveRooms,getActivity()));
        gv_liveroomsuggest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(getContext(), LiveRoomActivity.class);//从一个activity跳转到另一个activity  
                if(liveRooms.get(position).isIflive()){
                    if(liveRooms.get(position).getRoomName().contains("校")||liveRooms.get(position).getRoomName().contains("院"))
                        intent.putExtra("str", "0");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                    else
                        intent.putExtra("str", "1");
                }else {
                    intent.putExtra("str", "3");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                }
                startActivity(intent);
            }
        });

        search = (ImageView) view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {

                        Intent intent=new Intent();
                        intent.setClass(getContext(), LiveRoomActivity.class);//从一个activity跳转到另一个activity  
                        intent.putExtra("str", "1");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                        startActivity(intent);
                    }
                });
                searchFragment.show(getFragmentManager(),SearchFragment.TAG);
            }

        });
    }
}
