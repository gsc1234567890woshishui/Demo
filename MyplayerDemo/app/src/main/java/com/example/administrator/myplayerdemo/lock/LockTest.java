package com.example.administrator.myplayerdemo.lock;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.administrator.myplayerdemo.base.BaseAct;

import java.util.List;

import me.zhanghai.android.patternlock.PatternUtils;
import me.zhanghai.android.patternlock.PatternView;
import me.zhanghai.android.patternlock.SetPatternActivity;

/**
 * Created by Administrator on 2017/3/13 0013.
 * 设置密码锁
 */
public class LockTest extends SetPatternActivity {
    @Override
    protected void onSetPattern(List<PatternView.Cell> pattern) {
        String patternSha1 = PatternUtils.patternToSha1String(pattern);
        System.out.println("----设置的lock--"+patternSha1);
        // TODO: Save patternSha1 in SharedPreferences.
        SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        sp.edit().putString("key",patternSha1).commit();
    }
}
