package com.pft.quicktouch.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.pft.quicktouch.R;
import com.pft.quicktouch.http.CallBackUtil;
import com.pft.quicktouch.http.OkhttpUtil;
import com.pft.quicktouch.tool.NetTool;
import com.pft.quicktouch.tool.SpTool;
import com.pft.quicktouch.tool.ToastTool;
import com.pft.quicktouch.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 修改密码
 * 该页面逻辑比较简单，没有采用mvp模式
 */
public class ModifyPassActivity extends AppCompatActivity implements TitleBar.TitleBarClickListener {


    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.repass)
    EditText repass;
    @BindView(R.id.passtip)
    TextView passtip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pass);
        ButterKnife.bind(this);
        initview();

    }

    /**
     * 修改密码
     *
     * @param v
     */
    public void submit(View v) {
        String passStr = pass.getText().toString().trim();
        String repassStr = repass.getText().toString().trim();
        if (TextUtils.isEmpty(passStr) || TextUtils.isEmpty(repassStr)) {
            ToastTool.showToast(ModifyPassActivity.this, "请完成所有的内容填写");
        } else {
            if (passStr.equals(repassStr)) {
                //开始提交
                modify();

            } else {
                ToastTool.showToast(ModifyPassActivity.this, "两次密码不一致");
            }
        }
    }

    /**
     * 提交修改请求
     */
    private void modify() {
        if (NetTool.checkNetandWifi(ModifyPassActivity.this)) {
            String url = "";
            OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {
                    ToastTool.showToast(ModifyPassActivity.this, e.toString());
                }

                @Override
                public void onResponse(String response) {
                    //根据返回的状态码确定是否设置成功
                    //成功：跳转到登录页面，并清除之前保存的登录状态为false


                    ToastTool.showToast(ModifyPassActivity.this, "设置成功");
                    SpTool.clearLoginCount();//修改登录状态为false
                    startActivity(new Intent(ModifyPassActivity.this, LoginActivity.class));
                    finish();

                    //失败：toast错误提示

                }
            });
        }
    }

    /**
     * 初始化控件
     */

    private void initview() {
        passtip.setText("密码长度为8-16位，须包含数字和字母两种元素！");
        titlebar.setBackgroundColor(Color.parseColor("#0061AE"));
        titlebar.setTitle("修改密码");
        titlebar.setTopBarClickListener(this);

    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
