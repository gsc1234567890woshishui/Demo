package com.example.administrator.myplayerdemo.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myplayerdemo.R;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;

/**
 * Created by Administrator on 2017/3/14 0014.
 * 包含scrollview 的下拉刷新
 */
public class Fragment5 extends Fragment {
    private TextView tv;
    private BGARefreshLayout bga;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView tv=new TextView(getActivity());
//        tv.setText(getClass().getSimpleName());
        View v=View.inflate(getContext(), R.layout.f5,null);
        tv= (TextView) v.findViewById(R.id.tv);
        bga= (BGARefreshLayout) v.findViewById(R.id.bga);
        initbga(bga);
        return v;
    }

    private void initbga(final BGARefreshLayout bga) {
        bga.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing() {
                tv.setText("1");
                bga.endRefreshing();
            }

            @Override
            public void onBGARefreshLayoutBeginLoadingMore() {
                tv.setText("100");
                bga.endRefreshing();
            }
        });
        bga.setRefreshViewHolder(new BGAStickinessRefreshViewHolder(getContext(),true));
        bga.setCustomHeaderView(DataEngine.getCustomHeaderOrFooterView(getActivity()),false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
