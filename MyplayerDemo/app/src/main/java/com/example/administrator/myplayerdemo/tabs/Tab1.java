package com.example.administrator.myplayerdemo.tabs;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SwitchCompat;
import com.astuetz.PagerSlidingTabStrip;
import com.example.administrator.myplayerdemo.R;
import com.example.administrator.myplayerdemo.base.BaseAct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class Tab1 extends FragmentActivity implements ViewPager.OnPageChangeListener {
    private PagerSlidingTabStrip tab;
    private ViewPager vp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);
        tab= (PagerSlidingTabStrip) findViewById(R.id.pt);
        vp= (ViewPager) findViewById(R.id.vp);
        List<Fragment> frags=new ArrayList<>();
        frags.add(new Fragment1());
        frags.add(new Fragment2());
        frags.add(new Fragment3());
        frags.add(new Fragment4());
        frags.add(new Fragment5());
        String[] titles={"a","b","c","d","e"};
        vp.setAdapter(new MyVpAdapter(getSupportFragmentManager(),frags,titles));
        tab.setViewPager(vp);
        tab.setOnPageChangeListener(this);
        /*各种属性
                pstsIndicatorColor Color of the sliding indicator
        pstsUnderlineColor Color of the full-width line on the bottom of the view
        pstsDividerColor Color of the dividers between tabs
        pstsIndicatorHeightHeight of the sliding indicator
        pstsUnderlineHeight Height of the full-width line on the bottom of the view
        pstsDividerPadding Top and bottom padding of the dividers
        pstsTabPaddingLeftRight Left and right padding of each tab
        pstsScrollOffset Scroll offset of the selected tab
        pstsTabBackground Background drawable of each tab, should be a StateListDrawable
        pstsShouldExpand If set to true, each tab is given the same weight, default false
        pstsTextAllCaps If true, all tab titles will be upper case, default true
         */
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
