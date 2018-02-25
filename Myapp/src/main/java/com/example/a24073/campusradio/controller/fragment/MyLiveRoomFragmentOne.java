package com.example.a24073.campusradio.controller.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.adapter.LiveRoomConversationAdapter;
import com.example.a24073.campusradio.model.bean.ConversationMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyLiveRoomFragmentOne extends Fragment {


    ListView listView;
    ImageView send;
    EditText message;
    List<ConversationMsg> conversationMsgs;
    LiveRoomConversationAdapter liveRoomConversationAdapter;
    int type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conversationMsgs = new ArrayList<>();
        /*
        conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait1,"你好啊",false));
        conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait2,"你好啊",false));
        conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait3,"你好啊",false));
        conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait4,"你好啊",false));
        conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait5,"你好啊",false));
        conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait1,"你好啊2",true));
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_live_room_fragment_one, container, false);
    }
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.lv_liveroom_conversation);
        message = (EditText) view.findViewById(R.id.conversation_editText);
        send = (ImageView) view.findViewById(R.id.conversation_send);
        send.requestFocus();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConversationMsg conversationMsg = new ConversationMsg(R.drawable.ic_headportrait1, message.getText().toString(), true);
                message.setText("");
                conversationMsgs.add(conversationMsg);
                liveRoomConversationAdapter.notifyDataSetChanged();
            }
        });
        send.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ConversationMsg conversationMsg = new ConversationMsg(R.drawable.ic_headportrait2,"啦啦啦",false);
                conversationMsgs.add(conversationMsg);
                liveRoomConversationAdapter.notifyDataSetChanged();
                return true;
            }
        });

        liveRoomConversationAdapter = new LiveRoomConversationAdapter(conversationMsgs,getActivity());
        listView.setAdapter(liveRoomConversationAdapter);
    }

}
