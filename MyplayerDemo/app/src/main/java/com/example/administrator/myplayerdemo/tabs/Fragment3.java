package com.example.administrator.myplayerdemo.tabs;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myplayerdemo.R;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/3/14 0014.
 * 下拉刷新的listview使用
 */
public class Fragment3 extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private ListView lv;
    private BGARefreshLayout rl_listview_refresh;
    private ArrayList<String> mdatas;
    private ArrayAdapter<String> arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view=View.inflate(getContext(), R.layout.lvsx,null);
        initRefreshLayout(view);
        initListView(view) ;
        return view;
    }
    private void initListView(View view) {
        lv= (ListView) view.findViewById(R.id.lv_listview_data);
       mdatas=new ArrayList<>();
        for(int i=0;i<30;i++){
            mdatas.add("第"+(i+1)+"条目");

        }
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, mdatas);
        lv.setAdapter(arrayAdapter);

    }
    private void initRefreshLayout(View view) {
        rl_listview_refresh= (BGARefreshLayout) view.findViewById(R.id.rl_listview_refresh);
        rl_listview_refresh.setDelegate(this);//设置刷新监听
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(getActivity(), true);
        moocStyleRefreshViewHolder.setUltimateColor(Color.rgb(0, 0, 255));
        moocStyleRefreshViewHolder.setOriginalBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.iqegg));
        moocStyleRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.custom_green);
        moocStyleRefreshViewHolder.setSpringDistanceScale(0.2f);
        moocStyleRefreshViewHolder.setRefreshViewBackgroundColorRes(R.color.custom_green);
        rl_listview_refresh.setRefreshViewHolder(moocStyleRefreshViewHolder);//设置刷新样式
        rl_listview_refresh.setCustomHeaderView(DataEngine.getCustomHeaderOrFooterView(getActivity()), true);//设置广告图
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
int  j=1;
    int k=1;
    @Override
    public void onBGARefreshLayoutBeginRefreshing() {
       new Thread(){
           @Override
           public void run() {
               super.run();
               SystemClock.sleep(2000);
               mdatas.add("BeginRefreshing"+(++j));

               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       arrayAdapter.notifyDataSetChanged();
                       rl_listview_refresh.endRefreshing();
                   }
               });

           }
       }.start();
    }

    @Override
    public void onBGARefreshLayoutBeginLoadingMore() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(2000);
                mdatas.add("onBGARefreshLayoutBeginLoadingMore()"+(++k));

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        arrayAdapter.notifyDataSetChanged();
                        rl_listview_refresh.endRefreshing();
                    }
                });
            }
        }.start();
    }
}
