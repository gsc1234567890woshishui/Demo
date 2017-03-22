package com.example.administrator.myplayerdemo.tabs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myplayerdemo.R;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;

/**
 * Created by Administrator on 2017/3/14 0014.
 * 一般的view的下拉刷新
 */
public class Fragment4 extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private TextView tv;
    private BGARefreshLayout bga;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView tv=new TextView(getActivity());
//        tv.setText(getClass().getSimpleName());
        View view=View.inflate(getContext(), R.layout.f4,null);
        tv= (TextView) view.findViewById(R.id.tv);
        bga= (BGARefreshLayout) view.findViewById(R.id.bga);
        initbga(bga);

        return view;
    }

    private void initbga(BGARefreshLayout bga) {
       // bga.setRefreshViewHolder(new BGANormalRefreshViewHolder(getContext(),true));
      bga.setRefreshViewHolder(new BGAStickinessRefreshViewHolder(getContext(),true));
        bga.setCustomHeaderView(DataEngine.getCustomHeaderOrFooterView(getActivity()),true);
        //bga.setRefreshViewHolder(new BGAMoocStyleRefreshViewHolder(getActivity(),true));
        //mRefreshLayout.setDelegate(this);
       // mRefreshLayout.setCustomHeaderView(DataEngine.getCustomHeaderOrFooterView(this), true);
//        BGAStickinessRefreshViewHolder stickinessRefreshViewHolder = new BGAStickinessRefreshViewHolder(this, true);
//        stickinessRefreshViewHolder.setStickinessColor(Color.parseColor("#11cd6e"));
//        stickinessRefreshViewHolder.setRotateDrawable(getResources().getDrawable(R.mipmap.custom_stickiness_roate));
//        mRefreshLayout.setRefreshViewHolder(stickinessRefreshViewHolder)
        // mRefreshLayout.setCustomHeaderView(DataEngine.getCustomHeaderOrFooterView(this), false);
       // mRefreshLayout.setRefreshViewHolder(new BGAMoocStyleRefreshViewHolder(this, true));
        bga.setDelegate(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing() {
        tv.setText("onBGARefreshLayoutBeginRefreshing()");
        bga.endRefreshing();
    }

    @Override
    public void onBGARefreshLayoutBeginLoadingMore() {
        tv.setText("onBGARefreshLayoutBeginLoadingMore()");
        bga.endRefreshing();
    }
}
