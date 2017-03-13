package com.example.administrator.myplayerdemo;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class VideoInfo {
    String displayName;
    String path;
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPath(String path) {
        this.path = path;
    }
    String vedioName;
    public void setVedioName(String vedioName) {
        this.vedioName = vedioName;
    }
    String data;
    public void setData(String data) {
        this.data = data;
    }
    long duration;
    public void setDuration(long duration) {
        this.duration = duration;
    }
    long size;
    public void setSize(long size) {
        this.size = size;
    }
    Bitmap thumImage;
    public void setThumImage(Bitmap thumImage) {
        this.thumImage = thumImage;
    }
}
