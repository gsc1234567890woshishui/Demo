package com.example.administrator.myplayerdemo.rvadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.myplayerdemo.R;
import com.example.administrator.myplayerdemo.interfaces.Irecyler;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public class MyrvAdapter extends RecyclerView.Adapter<MyrvAdapter.Vh> {
    private List<String> urls;
    private Context ctx;
Irecyler is;

    public void setIs(Irecyler is) {
        this.is = is;
    }

    public MyrvAdapter(List<String> urls, Context ctx) {
        this.urls = urls;
        this.ctx = ctx;
    }

    @Override
    public MyrvAdapter.Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vh(LayoutInflater.from(parent.getContext()).inflate(R.layout.img,parent,false));
    }
    @Override
    public void onBindViewHolder(final MyrvAdapter.Vh holder, int position) {
        Glide.with(ctx).load(urls.get(position)).into(holder.iv);
        if (is!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    is.OnItemcliclklistenrs(v,holder.getLayoutPosition());

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    is.OnItemLongcliclklistenrs(v,holder.getLayoutPosition());
                    return true;
                }
            });
        }


    }
    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        private ImageView iv;
        public Vh(View itemView) {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
