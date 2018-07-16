package com.pft.quicktouch.view;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 倒计时
 */

public class CountDownTimerUtils extends CountDownTimer {
    TextView mView;

    public CountDownTimerUtils(TextView view, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mView = view;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mView.setClickable(false); //设置不可点击
        mView.setEnabled(false);
        mView.setText(millisUntilFinished / 1000 + "秒后可重新发送");  //设置倒计时时间
        mView.setBackgroundColor(Color.parseColor("#e0e0e0")); //设置按钮为灰色，这时是不能点击的

    }

    @Override
    public void onFinish() {
        mView.setEnabled(true);
        mView.setText("发送验证码");
        mView.setBackgroundColor(Color.parseColor("#0061AE"));

    }
}
