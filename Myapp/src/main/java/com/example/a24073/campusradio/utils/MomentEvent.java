package com.example.a24073.campusradio.utils;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by 24073 on 2017/7/20.
 */

public class MomentEvent {
    String text;
    Bitmap bitmap;
    Date date;

    public MomentEvent(String text, Bitmap bitmap, Date date) {
        this.text = text;
        this.bitmap = bitmap;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
