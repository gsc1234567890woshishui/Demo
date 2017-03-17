package com.example.administrator.myplayerdemo.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myplayerdemo.R;
import com.example.administrator.myplayerdemo.interfaces.Irecyler;
import com.example.administrator.myplayerdemo.rvadapters.MyrvAdapter;

/**
 * Created by Administrator on 2017/3/14 0014.
 * 瀑布流实现案例01
 */

public class Fragment2 extends Fragment {
    private RecyclerView rv;
    private MyrvAdapter myrvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView tv=new TextView(getActivity());
//        tv.setText(getClass().getSimpleName());
        View inflate = inflater.inflate(R.layout.pbl, container, false);
        findviews(inflate);
        return inflate;
    }

    private void findviews(View inflate) {
        rv= (RecyclerView) inflate.findViewById(R.id.rv);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        String[] urls={"http://img03.sogoucdn.com/app/a/100520024/3241edbf8cff390d3d6fa5645ee18996","http://pic.sogou.com/pics/recompic/detail.jsp?category=%E7%BE%8E%E5%A5%B3&tag=%E5%85%A8%E9%83%A8#2%2612638654","http://img01.sogoucdn.com/app/a/100520024/90e5ba8d1446facd2214116c72b0c937","http://img04.sogoucdn.com/app/a/100520024/4e0fb3ea2b62611b9264dda03c117bf3","http://pic.sogou.com/pics/recompic/detail.jsp?category=%E7%BE%8E%E5%A5%B3&tag=%E5%A5%B3%E7%A5%9E#2%2613413723","http://img01.sogoucdn.com/app/a/100520024/155f5b7e4e983bf9c6146833f53b9148","http://img01.sogoucdn.com/app/a/100520024/ba5e6265893f23e1fbd94ca455aa7ce9","http://img02.sogoucdn.com/app/a/100520024/a5798fbd4f8efdc43219b656df03508a","http://img03.sogoucdn.com/app/a/100520024/5d0a80753b68af0ac380b682d7ad3409","http://img03.sogoucdn.com/app/a/100520024/8a82c2c2578266db3178b87898124765","http://img03.sogoucdn.com/app/a/100520024/2e219bb628f2a49b9371bb7592c03e12","http://img01.sogoucdn.com/app/a/100520024/2eae8ed9eacf14c00fed2dadfbc49d44","http://img01.sogoucdn.com/app/a/100520024/56f9c880e7a7dc58184427561e1c774d"};
        myrvAdapter = new MyrvAdapter(urls, getActivity());
        rv.setAdapter(myrvAdapter);
        myrvAdapter.setIs(new Irecyler() {
            @Override
            public void OnItemcliclklistenrs(View view, int pos) {
                Toast.makeText(getContext(),"点击了"+pos,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongcliclklistenrs(View view, int pos) {
                Toast.makeText(getContext(),"长按了"+pos,Toast.LENGTH_SHORT).show();
            }
        });


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
