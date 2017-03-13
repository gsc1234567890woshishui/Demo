package com.example.administrator.myplayerdemo.activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.myplayerdemo.QRUtils;
import com.example.administrator.myplayerdemo.R;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class Qrimageshow extends Activity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String str="http://live.gslb.letv.com/gslb?stream_id=lb_hkmovie_1300&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1";
        setContentView(R.layout.imge);
        iv= (ImageView) findViewById(R.id.iv);
        Bitmap qrImage = QRUtils.createQRImage(str);
        iv.setImageBitmap(qrImage);
    }
}
