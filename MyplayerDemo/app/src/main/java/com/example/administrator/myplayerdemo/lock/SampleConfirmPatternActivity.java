package com.example.administrator.myplayerdemo.lock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;

import me.zhanghai.android.patternlock.ConfirmPatternActivity;
import me.zhanghai.android.patternlock.PatternUtils;
import me.zhanghai.android.patternlock.PatternView;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
public class SampleConfirmPatternActivity extends ConfirmPatternActivity {
    protected boolean isStealthModeEnabled() {
        // TODO: Return the value from SharedPreferences.
        return false;
    }
    @Override
    protected boolean isPatternCorrect(List<PatternView.Cell> pattern) {
        // TODO: Get saved pattern sha1.
        SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        String key = sp.getString("key", "");
        return TextUtils.equals(PatternUtils.patternToSha1String(pattern), key);
    }

    /**
     * 密码成功
     */
    @Override
    protected void onConfirmed() {
        super.onConfirmed();
        Toast.makeText(getApplicationContext(),"密码成功！",Toast.LENGTH_LONG).show();
    }

    /**
     * 忘记密码
     */
    @Override
    protected void onForgotPassword() {

        //startActivity(new Intent(this, YourResetPatternActivity.class));

        // Finish with RESULT_FORGOT_PASSWORD.
        super.onForgotPassword();
    }
}
