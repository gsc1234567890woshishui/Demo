package com.example.administrator.myplayerdemo.activitys;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.administrator.myplayerdemo.R;
import com.example.administrator.myplayerdemo.fileutils.AppUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;
import is.arontibo.library.ElasticDownloadView;

/**
 * Created by Administrator on 2017/3/27 0027.
 */
public class FileDownappXutilsDemo  extends Activity implements View.OnClickListener {
    private Button btn;
    private RingProgressBar pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuils3);
        btn= (Button) findViewById(R.id.btn);

        pd= (RingProgressBar) findViewById(R.id.progress_bar_2);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        // 先判断是否有权限。
        if(AndPermission.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // 有权限，直接do anything.
            dapp();
        } else {
            // 申请权限。
            AndPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .send();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，第一个参数是当前Acitivity/Fragment，回调方法写在当前Activity/Framgent。
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    // 成功回调的方法，用注解即可，里面的数字是请求时的requestCode。
    @PermissionYes(100)
    private void getLocationYes(List<String> grantedPermissions) {
        // TODO 申请权限成功。
       dapp();
    }
    // 失败回调的方法，用注解即可，里面的数字是请求时的requestCode。
    @PermissionNo(100)
    private void getLocationNo(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            // 第一种：用默认的提示语。
            //AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING).show();
            Toast.makeText(getApplicationContext(),"权限被拒绝....",Toast.LENGTH_LONG).show();
        }
    }
    private void dapp() {
        RequestParams params = new RequestParams("http://api.nohttp.net/download/1.apk");
        params.setAutoRename(true);//断点下载
        params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/demo.apk");
        x.http().get(params, new Callback.ProgressCallback<File>() {

            @Override
            public void onSuccess(File result) {
               ;  Log.i("xutils","onSuccess"+result.getAbsolutePath());
                //pd.dismiss();

                AppUtils.installapp(FileDownappXutilsDemo.this,result.getPath());

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("xutils","onError");
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.i("xutils","onCancelled");
            }
            @Override
            public void onFinished() {
//                pd.dismiss();
//                Log.i("xutils","onFinished()");
            }
            @Override
            public void onWaiting() {
                Log.i("xutils","onWaiting()");
            }
            @Override
            public void onStarted() {

//          pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                pd.setProgressNumberFormat("%1d b/%2d b");
//                pd.setTitle("正在下载中..");
//                pd.setMessage("请等待.....");
//
//                pd.show();
                pd.setProgress(0);


            }
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                pd.setProgress((int) (current));

//                ev.setMax((int)total);
                pd.setMax((int)total);
                DecimalFormat df2  = new DecimalFormat("###.00");//这样为保持2位
               System.out.println("当前大小="+df2.format(current/1024/1024));
                System.out.println("总共大小="+df2.format(total/1024/1024));
//                float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
//                 b = new BigDecimal( (float) total);
//                float v = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

               // pd.setProgress((int)current);
            }
        });
    }
}
