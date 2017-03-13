package com.example.administrator.myplayerdemo.views;

import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.administrator.myplayerdemo.R;
import com.example.administrator.myplayerdemo.base.BaseAct;
import com.example.parallaxmylib.ParallaxListView;
/**
 * Created by Administrator on 2017/3/13 0013.
 * 阻尼效果view QQ支付宝
 */
public class Zuniview extends BaseAct {
    private ParallaxListView pl;
    @Override
    protected void doBusy() {
        pl=findviews(R.id.lv);
        ImageView iv=new ImageView(this);
        AbsListView.LayoutParams l=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
        iv.setLayoutParams(l);
       // iv.setBackgroundResource(R.mipmap.movie);
        iv.setImageResource(R.mipmap.movie);
       pl.addHeaderView(iv);
       pl.setImageViewToParallax(iv);
        String[] dummys={"app开发","java开发","go开发","php开发","ios开发"};
        pl.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dummys));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.zuniview;
    }
}
