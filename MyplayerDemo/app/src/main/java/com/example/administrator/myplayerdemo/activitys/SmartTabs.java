package com.example.administrator.myplayerdemo.activitys;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.example.administrator.myplayerdemo.R;
import com.example.administrator.myplayerdemo.base.BaseAct;
import com.example.administrator.myplayerdemo.tabs.Fragment1;
import com.example.administrator.myplayerdemo.tabs.Fragment2;
import com.example.administrator.myplayerdemo.tabs.Fragment3;
import com.example.administrator.myplayerdemo.tabs.Fragment4;
import com.example.administrator.myplayerdemo.tabs.Fragment5;
import com.example.administrator.myplayerdemo.tabs.MyVpAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class SmartTabs extends BaseAct {
    @Override
    protected void doBusy() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        List<Fragment> list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());
        list.add(new Fragment5());
        String[] strs = {"s1", "s2", "s3", "s4", "s5"};
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        creator.add("tile1", Fragment1.class)
                .add("tile2", Fragment2.class);
        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(new MyVpAdapter(getSupportFragmentManager(),list,strs));
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.smarttabs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
}
