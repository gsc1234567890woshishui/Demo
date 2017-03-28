package com.example.administrator.myplayerdemo.fileutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class AppUtils {
    public static void installapp(Context ctx, String path){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + path),"application/vnd.android.package-archive");
        ctx.startActivity(intent);
    }
}
