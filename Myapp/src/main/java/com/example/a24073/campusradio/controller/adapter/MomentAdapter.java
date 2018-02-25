package com.example.a24073.campusradio.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.a24073.campusradio.MyResource.CircleImageView;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.model.bean.Message;
import com.ramotion.foldingcell.FoldingCell;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static android.R.attr.width;
import static in.arjunsn.constraintlayoutdemo.R.attr.height;

/**
 * Created by 24073 on 2017/7/13.
 */

public class MomentAdapter extends BaseAdapter {

    private List<Message> messages;
    private Context mContext;



    public MomentAdapter(List<Message> messages, Context mContext) {
        this.messages = messages;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Message message = (Message) getItem(position);
       if(((Message)getItem(position)).getPicture() == null) {
           view = View.inflate(mContext, R.layout.foldingcell_text, null);

           TextView name = (TextView) view.findViewById(R.id.profile_name);
           CircleImageView portrait = (CircleImageView) view.findViewById(R.id.profile_picture);
           TextView time = (TextView) view.findViewById(R.id.timestamp);
           TextView text = (TextView) view.findViewById(R.id.post_content_text);
           name.setText(message.getSenderName());//设置名字
           DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
           time.setText(message.getSenderSchool() + " " + df.format(message.getSendTime()));//设置学校和发送时间
           text.setText(message.getWords());//设置文字内容

           Glide.with(mContext)//设置头像
                   .load(message.getSenderIcon())
                   .dontAnimate()
                   .placeholder(R.drawable.a)
                   .into(portrait);


           TextView name2 = (TextView) view.findViewById(R.id.profile_name2);
           CircleImageView portrait2 = (CircleImageView) view.findViewById(R.id.profile_picture2);
           TextView time2 = (TextView) view.findViewById(R.id.timestamp2);
           TextView text2 = (TextView) view.findViewById(R.id.post_content_text2);
           name2.setText(message.getSenderName());//设置名字
           DateFormat df2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
           time2.setText(message.getSenderSchool() + " " + df2.format(message.getSendTime()));//设置学校和发送时间
           text2.setText(message.getWords());//设置文字内容

           Glide.with(mContext)//设置头像
                   .load(message.getSenderIcon())
                   .dontAnimate()
                   .placeholder(R.drawable.a)
                   .into(portrait2);


           final FoldingCell fc = (FoldingCell) view.findViewById(R.id.folding_cell);
           // attach click listener to folding cell
           fc.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   fc.toggle(false);
               }
           });

       }
       else {
           view = View.inflate(mContext, R.layout.foldingcell, null);

           TextView name = (TextView) view.findViewById(R.id.profile_name);
           CircleImageView portrait = (CircleImageView) view.findViewById(R.id.profile_picture);
           TextView time = (TextView) view.findViewById(R.id.timestamp);
           TextView text = (TextView) view.findViewById(R.id.post_content_text);
           ImageView imageView = (ImageView) view.findViewById(R.id.image_content);

           DateFormat df=new SimpleDateFormat("yyyy-MM-dd EE hh:mm:ss");

           name.setText(message.getSenderName());//设置名字
           time.setText(message.getSenderSchool() + " " + df.format(message.getSendTime()));//设置学校和发送时间
           text.setText(message.getWords());//设置文字内容

           Glide.with(mContext)  //设置图片
                   .load(message.getPicture())
                   .placeholder(R.drawable.a)
                   .crossFade()
                   .into(imageView);

           Glide.with(mContext)//设置头像
                   .load(message.getSenderIcon())
                   .dontAnimate()
                   .placeholder(R.drawable.a)
                   .into(portrait);



           TextView name2 = (TextView) view.findViewById(R.id.profile_name2);
           CircleImageView portrait2 = (CircleImageView) view.findViewById(R.id.profile_picture2);
           TextView time2 = (TextView) view.findViewById(R.id.timestamp2);
           TextView text2 = (TextView) view.findViewById(R.id.post_content_text2);
           ImageView imageView2 = (ImageView) view.findViewById(R.id.image_content2);
           name2.setText(message.getSenderName());//设置名字
           DateFormat df2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
           time2.setText(message.getSenderSchool() + " " + df2.format(message.getSendTime()));//设置学校和发送时间
           text2.setText(message.getWords());//设置文字内容

           Glide.with(mContext)  //设置图片
                   .load(message.getPicture())
                   .placeholder(R.drawable.a)
                   .crossFade()
                   .into(imageView2);

           Glide.with(mContext)//设置头像
                   .load(message.getSenderIcon())
                   .dontAnimate()
                   .placeholder(R.drawable.a)
                   .into(portrait2);

           final FoldingCell fc = (FoldingCell) view.findViewById(R.id.folding_cell);
           // attach click listener to folding cell
           fc.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   fc.toggle(false);
               }
           });
       }

        return view;
    }


}
