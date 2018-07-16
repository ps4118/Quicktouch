package com.pft.quicktouch.mvp.contract;


import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

import java.util.Calendar;

import javax.security.auth.callback.Callback;

public class LoginContract {

    public interface LoginView extends BaseView {
        void loginSuccess();

        /**
         * 登录失败
         *
         * @param errorMsg 提示信息
         */
        void loginError(String errorMsg);
    }

    public interface LoginModel {
        /**
         * 登录
         *
         * @param phone 手机号
         * @param pass  密码
         */
        void login(String url, String phone, String pass, ResultCallBack Callback);

    }


}
