package com.example.appver2;

import android.media.Image;



public class VerticalData {

    int img;

    String text;


    public VerticalData(int img, String text){
        this.img = img;
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public int getImg() {
        return this.img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setText(String text) {
        this.text = text;
    }

}

