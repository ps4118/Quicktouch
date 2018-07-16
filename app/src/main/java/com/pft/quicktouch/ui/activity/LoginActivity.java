package com.pft.quicktouch.ui.activity;


import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.pft.quicktouch.MyApplicaiton;
import com.pft.quicktouch.R;
import com.pft.quicktouch.mvp.contract.LoginContract;
import com.pft.quicktouch.mvp.presenter.LoginPresenter;
import com.pft.quicktouch.tool.NetTool;
import com.pft.quicktouch.tool.SpTool;
import com.pft.quicktouch.tool.ToastTool;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.LoginView, LoginPresenter> implements LoginContract.LoginView {


    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.forgetPass)
    TextView forgetPass;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initview() {
        phone.setText(MyApplicaiton.phone);

    }

    @Override
    protected void initdata() {

    }

    /**
     * 忘记密码
     */
    @OnClick(R.id.forgetPass)
    public void toFindPass() {
        Intent intent = new Intent(LoginActivity.this, MessageActivity.class);
        intent.putExtra("action", "forget");
        startActivity(intent);
    }

    /**
     * 登录
     */
    @OnClick(R.id.login)
    public void login() {
        String phoneStr = phone.getText().toString().trim();
        String passStr = pass.getText().toString().trim();
        //登录请求url
        String url = "http://www.baidu.com";
        if (!NetTool.checkNetandWifi(LoginActivity.this)) {
            ToastTool.showToast(LoginActivity.this, "请检查网络设置");
        } else {
            mPresenter.login(url, phoneStr, passStr);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        if (dialog != null && dialog.isShowing()) {
            //隐藏等待框
            dialog.dismiss();
        }
//        WaitDialog.dismiss();
        //存放用户已经登录的值为true
        SpTool.putBooleanValue("userinfo", "isLogin", true);
        //存放用户名
        SpTool.putStringValue("userinfo", "phone", phone.getText().toString());

//        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        //跳转到主页
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void loginError(String errorMsg) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    LoadingDailog dialog;

    @Override
    public void onstart() {
//        请求开始操作, 显示提示框
//        WaitDialog.show(LoginActivity.this, "正在登录");
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("正在登录...");

        dialog = loadBuilder.create();
        dialog.show();
    }

    @Override
    public void onfinish() {

//        WaitDialog.dismiss();

    }

}
