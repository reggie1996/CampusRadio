package com.example.a24073.campusradio.controller.fragment;

import android.graphics.Color;
import android.net.Uri;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.base.BaseFragment;
import com.example.a24073.campusradio.controller.adapter.FriendAdapter;
import com.example.a24073.campusradio.model.bean.Friend;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.filetransfer.RequestOption;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * Created by 24073 on 2017/6/3.
 * 对讲界面
 */

public class RadioFragment extends BaseFragment{

    private View view;
    private TextView titlebar;
    private ListView lv_friends;
    private ImageButton bt_add_friend;
    private ImageButton bt_group_chat;
    private TextView tv_add_friend;
    private TextView tv_group_chat;
    private SearchView search_friend;

    private MaterialRefreshLayout materialRefreshLayout;

    List<Friend> friends;
    @Override
    protected View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_radio,null);
        titlebar = (TextView) (view.findViewById(R.id.title).findViewById(R.id.title_in));
        lv_friends = (ListView) view.findViewById(R.id.rc_list1);
        bt_add_friend = (ImageButton) view.findViewById(R.id.bt_add_friend);
        tv_add_friend = (TextView) view.findViewById(R.id.tv_add_friend);
        bt_group_chat = (ImageButton) view.findViewById(R.id.bt_group_chat);
        tv_group_chat = (TextView) view.findViewById(R.id.tv_group_chat);
        search_friend = (SearchView) view.findViewById(R.id.search_friend);
        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refreshfriends);


        friends = new ArrayList<Friend>();
        friends.add(new Friend(R.drawable.ic_headportrait2,R.drawable.ic_shu2,"昆廷特拉蒂诺","剑桥大学"));
        friends.add(new Friend(R.drawable.ic_headportrait3,R.drawable.ic_shu3,"杰西卡阿尔芭","斯坦福大学"));
        friends.add(new Friend(R.drawable.ic_headportrait4,R.drawable.ic_shu4,"坎耶维斯特","霍格沃兹大学"));
        friends.add(new Friend(R.drawable.ic_headportrait5,R.drawable.ic_shu1,"马特达蒙","麻省理工学院"));
        friends.add(new Friend(R.drawable.ic_headportrait7,R.drawable.ic_shu2,"老吴","格兰芬多大学"));
        friends.add(new Friend(R.drawable.ic_headportrait6,R.drawable.ic_shu3,"哈利波特","王者峡谷大学"));
        friends.add(new Friend(R.drawable.a,R.drawable.ic_shu4,"托尼斯塔克","赫奇帕奇大学"));
        friends.add(new Friend(R.drawable.ic_headportrait1,R.drawable.ic_shu1,"简李","哈佛大学"));


        lv_friends.setAdapter(new FriendAdapter(friends,getActivity()));

        //设置每个item的监听事件
        lv_friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //手机
/*
                String token = "OgEEvbqnwUZs6Cvzd9PMEfSO1OBKGtNwfiAGCTmCmgcQN5Hz3pcH1dutdDJ4uCNL4vff1lJgkUdSt9Zx6DTzDA==";
                RongIM.connect(token, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {}

                    @Override
                    public void onSuccess(String s) {}

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {}
                });
                RongIM.getInstance().setCurrentUserInfo(new UserInfo("001","laowu",Uri.parse("http://a3.qpic.cn/psb?/V121cQJl2s5so0/WEudqBJwcATn8Xx.EhbzMqfUpuEX*wFulp9SA6WpyZQ!/b/dD0BAAAAAAAA")));
                RongIM.getInstance().setMessageAttachedUserInfo(true);
                RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE,"002",friends.get(position).getName());

*/
                //电脑

                String token = "V0B+Vdv/1E85zj6BjLsKnX6vnJTP2r5Uui2adrx0YxNDcfurI03yfzsbg1MA+dcdbVR13TuQv84=";
                RongIM.connect(token, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {}

                    @Override
                    public void onSuccess(String s) {}

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {}
                });
                RongIM.getInstance().setCurrentUserInfo(new UserInfo("002","test2",Uri.parse("http://a3.qpic.cn/psb?/V121cQJl2s5so0/hd2y2*QV0NMiwIjuwMYtFBCaxGMbyaNTFiVGx1z20NM!/b/dD0BAAAAAAAA")));
                RongIM.getInstance().setMessageAttachedUserInfo(true);
                RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE,"001",friends.get(position).getName());

            }
        });

        //设置两个ImageButton的监听事件
        bt_add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "添加好友", Toast.LENGTH_SHORT).show();
            }
        });
        bt_group_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "群聊", Toast.LENGTH_SHORT).show();
            }
        });


        //设置下拉刷新
        int[] color = {Color.rgb(59,134,134),Color.rgb(121,189,154),Color.rgb(168,219,168)};
        materialRefreshLayout.setWaveColor(0x40ffffff);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(true);
        materialRefreshLayout.setProgressColors(color);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {

                new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // refresh complete
                                materialRefreshLayout.finishRefresh();
                            }
                        });
                    }
                }.start();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                // load more refresh complete
                materialRefreshLayout.finishRefreshLoadMore();
            }
        });

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        titlebar.setText("好友列表");
        bt_add_friend.setImageResource(R.drawable.ic_add_friend);
        tv_add_friend.setText("新朋友");
        bt_group_chat.setImageResource(R.drawable.ic_group_chat);
        tv_group_chat.setText("群聊");
        search_friend.setQueryHint("搜索");
    }

}
