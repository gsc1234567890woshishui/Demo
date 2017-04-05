package com.example.administrator.myplayerdemo.activitys;
import android.widget.ArrayAdapter;
import com.example.administrator.myplayerdemo.base.BaseList;
/**
 * Created by Administrator on 2017/4/5 0005.
 */
public class OKhttpDemo extends BaseList{
    @Override
    protected void doinit() {
        String[] mdatas={"get","post"};
        getListView().setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mdatas));
    }
}
