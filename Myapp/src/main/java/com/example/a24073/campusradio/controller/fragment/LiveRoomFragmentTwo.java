package com.example.a24073.campusradio.controller.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.utils.LiveRoomConversationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveRoomFragmentTwo extends Fragment {

    int type;
    ImageView iv_descri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_room_fragment_two, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_descri = (ImageView) view.findViewById(R.id.iv_descri);

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);  //注册
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }

    @Subscribe
    public void onEventMainThread(LiveRoomConversationEvent event){
        type = event.getType();

        switch (type){
            case 0://校园广播
                iv_descri.setImageResource(R.mipmap.livedescri2);
                break;
            case 1://私人广播
                iv_descri.setImageResource(R.mipmap.livedescri1);
                break;
            case 2://学习广播
                iv_descri.setImageResource(R.mipmap.livedescri3);
                break;
            case 3://未开播

                break;
            default:
                break;
        }
    }
}
