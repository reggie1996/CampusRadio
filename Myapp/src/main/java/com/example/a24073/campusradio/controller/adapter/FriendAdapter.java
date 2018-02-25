package com.example.a24073.campusradio.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a24073.campusradio.MyResource.CircleImageView;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.activity.MainActivity;
import com.example.a24073.campusradio.controller.fragment.RadioFragment;
import com.example.a24073.campusradio.model.bean.Friend;

import java.util.List;

/**
 * Created by 24073 on 2017/7/4.
 */

public class FriendAdapter extends BaseAdapter {
    private List<Friend> friends;
    private Context mContext;

    public FriendAdapter(List<Friend> friends, Context mContext) {
        this.friends = friends;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(mContext, R.layout.item_friend_adapter,null);

        Friend friend = friends.get(position);

        com.example.a24073.campusradio.MyResource.CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.portrait);
        TextView nameTV = (TextView) view.findViewById(R.id.friend_name);
        TextView idTV = (TextView) view.findViewById(R.id.friend_id);
        ImageView imageView = (ImageView) view.findViewById(R.id.shu);

        circleImageView.setImageResource(friend.getPortrait());
        imageView.setImageResource(friend.getShu());
        nameTV.setText(friend.getName());
        idTV.setText(friend.getId());


        return view;
    }
}
