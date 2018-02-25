package com.example.a24073.campusradio.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a24073.campusradio.MyResource.CircleImageView;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.model.bean.ClassConversation;

import java.util.List;

/**
 * Created by 24073 on 2017/7/30.
 */

public class ClassRoomConversatioonAdapter extends BaseAdapter {

    private List<ClassConversation> msgs;
    private Context mContext;

    public ClassRoomConversatioonAdapter(List<ClassConversation> msgs, Context mContext) {
        this.msgs = msgs;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return msgs.size();
    }

    @Override
    public Object getItem(int position) {
        return msgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ClassConversation message = (ClassConversation) getItem(position);
        if(message.isIfme()){
            view = View.inflate(mContext, R.layout.item_classroom_conversation_self, null);
            CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.conversation_portrait_self);
            circleImageView.setImageResource(message.getHead());
            TextView time = (TextView) view.findViewById(R.id.classtime);
            time.setText(message.getInfo());
        }else{
            view = View.inflate(mContext, R.layout.item_classroom_conversation, null);
            CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.conversation_portrait);
            circleImageView.setImageResource(message.getHead());
            TextView time = (TextView) view.findViewById(R.id.classtime);
            time.setText(message.getInfo());
        }
        return view;
    }
}
