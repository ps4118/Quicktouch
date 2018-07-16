package com.pft.quicktouch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pft.quicktouch.MyApplicaiton;
import com.pft.quicktouch.R;
import com.pft.quicktouch.mvp.contract.GetCodeContract;
import com.pft.quicktouch.mvp.presenter.GetCodePresenter;
import com.pft.quicktouch.tool.NetTool;
import com.pft.quicktouch.tool.ToastTool;
import com.pft.quicktouch.view.CountDownTimerUtils;
import com.pft.quicktouch.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改密码
 */
public class MessageActivity extends BaseActivity<GetCodeContract.GetCodeView, GetCodePresenter> implements GetCodeContract.GetCodeView, TitleBar.TitleBarClickListener {


    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.code)
    EditText code;
    //进入口（忘记密码：forget,修改密码：modify）
    String action = "";

    @Override
    protected GetCodePresenter createPresenter() {
        return new GetCodePresenter();
    }

    /**
     * 验证码验证
     *
     * @param v
     */
    public void submit(View v) {
        String url = "";
        mPresenter.vofifyCode(url, phone.getText().toString(), code.getText().toString());
    }


    @Override
    protected void initview() {
        titlebar.setTitle("短信验证");
        titlebar.setTopBarClickListener(this);
        action = getIntent().getStringExtra("action");
        if (action.equals("forget")) {
            phone.setText("");
            phone.setEnabled(true);

        } else {
//            phone.setText(MyApplicaiton.phone.substring(0, 3) + "****" + MyApplicaiton.phone.substring(7, 10));
            phone.setEnabled(false);
            phone.setText("123123123");
        }
        getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetTool.checkNetandWifi(MessageActivity.this)) {
                    String url = "";
                    mPresenter.getcode(url, phone.getText().toString());
                }
            }
        });


    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void getCodeStart() {
//        code.setBackgroundColor(Color.parseColor("#E0E0E0"));
        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(getcode, 60000, 1000);
        countDownTimerUtils.start();
    }

    @Override
    public void codeSuccess(String code) {

    }

    @Override
    public void codeError(String msg) {
        ToastTool.showToast(MessageActivity.this, msg);
    }

    @Override
    public void virifySuccess() {
        //验证成功，跳转到修改密码界面
        Intent intent = new Intent(MessageActivity.this, ModifyPassActivity.class);
        startActivity(intent);
    }

    @Override
    public void virifyError(String msg) {
        ToastTool.showToast(MessageActivity.this, msg);
//        Intent intent = new Intent(MessageActivity.this, ModifyPassActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onstart() {

    }

    @Override
    public void onfinish() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
