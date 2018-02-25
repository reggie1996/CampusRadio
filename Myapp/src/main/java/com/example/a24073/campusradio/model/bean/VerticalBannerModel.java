package com.example.a24073.campusradio.model.bean;

/**
 * Created by 24073 on 2017/7/13.
 */

public class VerticalBannerModel {
    private String text;
    private int image;

    public VerticalBannerModel(String text, int image) {
        this.text = text;
        this.image = image;
    }

    public VerticalBannerModel() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
