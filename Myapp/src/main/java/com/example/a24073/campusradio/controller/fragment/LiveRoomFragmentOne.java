package com.example.a24073.campusradio.controller.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.adapter.LiveRoomConversationAdapter;
import com.example.a24073.campusradio.model.bean.ConversationMsg;
import com.example.a24073.campusradio.utils.LiveRoomConversationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveRoomFragmentOne extends Fragment {

    ListView listView;
    ImageView send;
    EditText message;
    List<ConversationMsg> conversationMsgs;
    LiveRoomConversationAdapter liveRoomConversationAdapter;
    int type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);  //注册
        conversationMsgs = new ArrayList<>();
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
                conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait1,"又要开学了",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait2,"对啊，太可怕了",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait3,"可以见到新同学了",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait4,"放轻松",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait5,"对呢",false));
                //conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait1,"你好啊",true));

                break;
            case 1://私人广播
                conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait1,"哇",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.mainone_cover6,"哇，那你真的棒",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.mainone_cover2,"主播太强了",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait4,"很强很强",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.mainone_cover5,"封面看起来很好吃诶",false));
                //conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait1,"你好啊1",true));
                break;
            case 2://学习广播
                conversationMsgs.add(new ConversationMsg(R.drawable.mainone_cover3,"学到了",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait2,"今天又长知识了",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.mainone_cover9,"谢谢老师",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.mainone_cover6,"老师下次直播什么时候",false));
                conversationMsgs.add(new ConversationMsg(R.drawable.mainone_cover2,"我不禁陷入沉思",false));
                //conversationMsgs.add(new ConversationMsg(R.drawable.ic_headportrait1,"你好啊2",true));
                break;
            case 3://未开播
                break;
            default:
                break;
        }
        liveRoomConversationAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_room_fragment_one, container, false);
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
                ConversationMsg conversationMsg = new ConversationMsg(R.drawable.ic_headportrait2,"来新朋友了，嘿嘿",false);
                conversationMsgs.add(conversationMsg);
                liveRoomConversationAdapter.notifyDataSetChanged();
                return true;
            }
        });

        liveRoomConversationAdapter = new LiveRoomConversationAdapter(conversationMsgs,getActivity());
        listView.setAdapter(liveRoomConversationAdapter);
    }

}
