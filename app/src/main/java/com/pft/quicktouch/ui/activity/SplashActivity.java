package com.pft.quicktouch.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pft.quicktouch.MyApplicaiton;
import com.pft.quicktouch.R;
import com.pft.quicktouch.tool.SpTool;

/**
 * 引导页，用于判断进入登录页面还是主页面
 */
public class SplashActivity extends AppCompatActivity {
    public static final String TAG = SplashActivity.class.getSimpleName();
    //申请的权限
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        getIsLogin();
        getPhone();
        applyPermissions();

    }

    /**
     * 进入主页面申请权限
     */
    public void applyPermissions() {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, permissions, 321);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 321) {
            //根据账户上次登录状态，3s进入登录界面或者主页面
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (MyApplicaiton.isLogin) {
                        //上次已经登录,进入主页面
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } else {
                        //没有登录状态，进入登录界面
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                }
            }, 2000);
        }
    }

    /**
     * 获取上次的登录状态
     */
    void getIsLogin() {
        MyApplicaiton.isLogin = SpTool.gutBooleanValue("userinfo", "isLogin");
        Log.i(TAG, "保存登录状态----->" + MyApplicaiton.isLogin);
    }

    /**
     * 获取手机号
     */
    void getPhone() {
        MyApplicaiton.phone = SpTool.gutStringValue("userinfo", "phone");
        Log.i(TAG, "保存的手机号----->" + MyApplicaiton.phone);
    }
}
