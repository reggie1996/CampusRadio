package com.example.a24073.campusradio.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.example.a24073.campusradio.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

import static io.rong.push.RongPushClient.ConversationType.CHATROOM;


public class TestRongyunActivity extends AppCompatActivity {
    String token1 = "YKSoNAQc/nfNgTUneFwGefSO1OBKGtNwfiAGCTmCmgdh56JAsDLyOU/Ooyu40rTPQJwNk09wtcxSt9Zx6DTzDA==";
    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rongyun);
        String token = "Q+I5rkAztZJKj2krxYTCRH6vnJTP2r5Uui2adrx0YxM7KbeE0T8kn9oJlw4XegNPorfJQBe6gBM=";
        textView = (TextView) findViewById(R.id.textRong);
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                textView.setText("error");
            }
        });

        //聊天界面
       //RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE,"1111","test'");

        //聊天列表
        //RongIM.getInstance().startConversationList(this,null);
    }
}
