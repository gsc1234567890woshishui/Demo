package com.example.administrator.myplayerdemo.views;

import android.widget.ImageView;

import com.example.administrator.myplayerdemo.R;
import com.example.administrator.myplayerdemo.base.BaseAct;
import com.example.parallaxmylib.ParallaxScrollView;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class ParallaxScrollViewActivity extends BaseAct {
    private ImageView image;
    ParallaxScrollView parallax;
    @Override
    protected void doBusy() {
        parallax = (ParallaxScrollView) findViewById(R.id.sc);
        image = (ImageView) findViewById(R.id.iv);

        parallax.setImageViewToParallax(image);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.scroll;
    }
}
