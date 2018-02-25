package com.example.a24073.campusradio.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a24073.campusradio.MyResource.CircleImageView;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.model.bean.ConversationMsg;

import java.util.List;


/**
 * Created by 24073 on 2017/7/19.
 */

public class LiveRoomConversationAdapter extends BaseAdapter {

    private List<ConversationMsg> msgs;
    private Context mContext;

    public LiveRoomConversationAdapter(List<ConversationMsg> msgs, Context mContext) {
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
        ConversationMsg message = (ConversationMsg) getItem(position);
        if(message.isIfme()){
            view = View.inflate(mContext, R.layout.item_liveroom_conversation_self, null);
            CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.conversation_portrait_self);
            TextView textView = (TextView) view.findViewById(R.id.conversation_text_self);
            circleImageView.setImageResource(R.drawable.ic_headportrait1);
            textView.setText(message.getMsg());
        }else{
            view = View.inflate(mContext, R.layout.item_liveroom_conversation, null);
            CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.conversation_portrait);
            TextView textView = (TextView) view.findViewById(R.id.conversation_text);
            textView.setText(message.getMsg());
            circleImageView.setImageResource(message.getHead());
        }
        return view;
    }
}
