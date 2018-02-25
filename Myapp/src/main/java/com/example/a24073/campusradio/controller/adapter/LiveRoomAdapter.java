package com.example.a24073.campusradio.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.model.bean.LiveRoom;

import java.util.List;

/**
 * Created by 24073 on 2017/7/16.
 */

public class LiveRoomAdapter extends BaseAdapter {

    private List<LiveRoom> liverooms;
    private Context mContext;

    public LiveRoomAdapter(List<LiveRoom> liverooms, Context mContext) {
        this.liverooms = liverooms;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return liverooms.size();
    }

    @Override
    public Object getItem(int position) {
        return liverooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(mContext, R.layout.item_liveroom_adapter,null);
        LiveRoom liveRoom = (LiveRoom) getItem(position);

        ImageView iv_livecover = (ImageView) view.findViewById(R.id.iv_livecover);
        TextView tv_name_liveroom = (TextView) view.findViewById(R.id.tv_name_liveroom);
        TextView tv_name_host = (TextView) view.findViewById(R.id.tv_name_host);
        TextView tv_like_liveroom = (TextView) view.findViewById(R.id.tv_like_liveroom);
        ImageView iv_islive = (ImageView) view.findViewById(R.id.iv_livestatus);

        iv_livecover.setImageResource(R.drawable.mainone_cover1);

        Glide.with(mContext)
                .load(liveRoom.getLiveRoomCover())
                .dontAnimate()
                .placeholder(R.drawable.mainone_cover1)
                .into(iv_livecover);

        tv_name_liveroom.setText(liveRoom.getRoomName());
        tv_name_host.setText(liveRoom.getHostName());
        tv_like_liveroom.setText(liveRoom.getLikeNum() + "人");
        if(liveRoom.isIflive()) {
            Glide.with(mContext)
                    .load(R.drawable.ic_live)
                    .dontAnimate()
                    .placeholder(R.drawable.ic_live)
                    .into(iv_islive);
        }else {
            Glide.with(mContext)
                    .load(R.drawable.ic_nothome)
                    .dontAnimate()
                    .placeholder(R.drawable.ic_nothome)
                    .into(iv_islive);
        }
        return view;
    }
}
