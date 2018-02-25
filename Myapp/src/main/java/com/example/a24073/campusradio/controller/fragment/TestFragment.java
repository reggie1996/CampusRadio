package com.example.a24073.campusradio.controller.fragment;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brucetoo.gradienttabstrip.ColorGradientView;
import com.brucetoo.gradienttabstrip.GradientTextView;
import com.example.a24073.campusradio.R;

/**
 * Created by 24073 on 2017/7/8.
 */

public class TestFragment extends android.support.v4.app.Fragment {
    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_layout, null);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
