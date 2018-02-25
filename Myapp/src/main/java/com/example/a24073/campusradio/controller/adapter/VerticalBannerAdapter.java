package com.example.a24073.campusradio.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.fragment.MomentFragment;
import com.example.a24073.campusradio.model.bean.VerticalBannerModel;
import com.sdsmdg.tastytoast.TastyToast;
import com.taobao.library.BaseBannerAdapter;
import com.taobao.library.VerticalBannerView;

import java.util.List;

/**
 * Created by 24073 on 2017/7/13.
 */

public class VerticalBannerAdapter extends BaseBannerAdapter<VerticalBannerModel> {

    private List<VerticalBannerModel> mDatas;

    public VerticalBannerAdapter(List<VerticalBannerModel> datas) {
        super(datas);
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_verticalbanner,null);
    }

    @Override
    public void setItem(final View view, final VerticalBannerModel data) {
        TextView tv = (TextView) view.findViewById(R.id.tv_verticalbanner);
        tv.setText(data.getText());

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_verticalbanner);
        imageView.setImageResource(R.drawable.ic_verbanner);
        //你可以增加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(view.getContext(), data.getText(), TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
            }
        });
    }
}