package com.example.administrator.myplayerdemo.activitys;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.download.DownloadListener;
import com.download.FileDownloader;
import com.example.administrator.myplayerdemo.AppConfig;
import com.example.administrator.myplayerdemo.R;
import com.example.administrator.myplayerdemo.fileutils.AppUtils;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.ServerError;
import com.yanzhenjie.nohttp.error.StorageReadWriteError;
import com.yanzhenjie.nohttp.error.StorageSpaceNotEnoughError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 文件下载的demo
 * 注意Nohttp下载必须在真机上测试
 */
public class FileDownappDemo  extends Activity implements View.OnClickListener {
    private Button btn;
    //private ProgressBar pb;
    private RingProgressBar pd;
   // private TextView tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filedemo);
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
      // pb= (ProgressBar) findViewById(R.id.pb_progress);
        pd= (RingProgressBar) findViewById(R.id.progress_bar_2);
       // tv_result= (TextView) findViewById(R.id.tv_result);
    }

    public void downapp(){
      checkVersion("http://api.nohttp.net/download/1.apk");
    }
    /**
     * 检查版本更新
     * @param app_upload_url
     */
    private void checkVersion(String app_upload_url) {
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(
                app_upload_url, AppConfig.getInstance().APP_PATH_ROOT, "w.apk", true, true);
        NoHttp.getDownloadQueueInstance().add(0, downloadRequest, downloadListener);

}  private final static String PROGRESS_KEY = "download_single_progress";
    /**
     * 下载监听
     */
    private com.yanzhenjie.nohttp.download.DownloadListener downloadListener = new com.yanzhenjie.nohttp.download.DownloadListener() {

        @Override
        public void onStart(int what, boolean isResume, long beforeLength, Headers headers, long allCount) {
            int progress = AppConfig.getInstance().getInt(PROGRESS_KEY, 0);
            if (allCount != 0) {
                progress = (int) (beforeLength * 100 / allCount);
               // pb.setProgress(progress);
                pd.setProgress(progress);
            }
            updateProgress(progress, 0);


        }

        @Override
        public void onDownloadError(int what, Exception exception) {
            Log.i("gsc",exception.getMessage());


        }

        @Override
        public void onProgress(int what, int progress, long fileCount, long speed) {
            updateProgress(progress, speed);
           pd.setProgress(progress);
            AppConfig.getInstance().putInt(PROGRESS_KEY, progress);
        }

        @Override
        public void onFinish(int what, String filePath) {
            Logger.d("Download finish, file path: " + filePath);

        }

        @Override
        public void onCancel(int what) {

        }

        private void updateProgress(int progress, long speed) {
            double newSpeed = speed / 1024D;
            DecimalFormat decimalFormat = new DecimalFormat("###0.00");
            String sProgress = getString(R.string.download_progress);
            sProgress = String.format(Locale.getDefault(), sProgress, progress, decimalFormat.format(newSpeed));
            //tv_result.setText(sProgress);
        }
    };

    @Override
    public void onClick(View v) {
        // 先判断是否有权限。
        if(AndPermission.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // 有权限，直接do anything.
            downapp();
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
        downapp();
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
}