package com.example.administrator.myplayerdemo.activitys;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public class ThirdCenter extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] madtas={"二维码1","二维码2","二维码3","播放"};
        getListView().setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,madtas));
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(ThirdCenter.this,Qrimageshow.class));
                        break;
                    case 1:
                        startActivity(new Intent(ThirdCenter.this,Qrimageshow.class));
                        break;
                    case 2:
                        startActivity(new Intent(ThirdCenter.this,Qrimageshow.class));
                        break;
                    case 3:
                        startActivity(new Intent(ThirdCenter.this,Play.class));
                        break;
                }
            }
        });
    }
}
