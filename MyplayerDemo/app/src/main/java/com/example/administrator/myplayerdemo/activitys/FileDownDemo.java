package com.example.administrator.myplayerdemo.activitys;
import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.myplayerdemo.AppConfig;
import com.example.administrator.myplayerdemo.R;

import com.example.administrator.myplayerdemo.fileutils.AppUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;



import java.io.File;
import java.util.List;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/30 0030.
 */
public class FileDownDemo  extends Activity implements View.OnClickListener {

    private RingProgressBar b;
    private Button but,btn_stop;
    private BaseDownloadTask baseDownloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filedemo);
        b= (RingProgressBar) findViewById(R.id.progress_bar_2);
        but= (Button) findViewById(R.id.btn);
        btn_stop= (Button) findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(this);
        but.setOnClickListener(this);
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
        downApp();
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

    private void downApp() {
       baseDownloadTask = FileDownloader.getImpl().create("http://api.nohttp.net/download/1.apk")
                .setPath(Environment.getExternalStorageDirectory() + "/app.apk", false).setForceReDownload(true)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        b.setProgress(soFarBytes);
                        b.setMax(totalBytes);

                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        Log.i("gsc", "下载完成=" + task.getPath());
                        AppUtils.installapp(FileDownDemo.this, task.getPath());
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                    }
                });
        baseDownloadTask.start();

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_stop){
            baseDownloadTask.pause();
        }
        if (v.getId()==R.id.btn){
            // 先判断是否有权限。
            if(AndPermission.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // 有权限，直接do anything.
                downApp();
            } else {
                // 申请权限。
                AndPermission.with(this)
                        .requestCode(100)
                        .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .send();
            }
        }

    }
}
