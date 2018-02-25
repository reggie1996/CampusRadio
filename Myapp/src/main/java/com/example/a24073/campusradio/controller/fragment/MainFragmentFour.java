package com.example.a24073.campusradio.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.activity.ClassRoomActivity;
import com.example.a24073.campusradio.controller.adapter.MomentAdapter;
import com.example.a24073.campusradio.model.bean.Message;
import com.example.a24073.campusradio.testFastjson.testJson;
import com.example.a24073.campusradio.testVolley.TestVolleyActivity;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24073 on 2017/7/9.
 */

public class MainFragmentFour extends Fragment {

    ImageView classroom;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mainfour, null);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        classroom = (ImageView) view.findViewById(R.id.enterclassroom);
        classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ClassRoomActivity.class));
            }
        });
    }
}
