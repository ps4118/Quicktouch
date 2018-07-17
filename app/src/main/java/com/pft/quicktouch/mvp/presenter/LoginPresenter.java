package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.LoginContract;
import com.pft.quicktouch.mvp.model.LoginModelImpl;

import java.util.Map;

/**
 * 登录操作
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginView> {

    private LoginModelImpl mLoginModel;

    public LoginPresenter() {
        mLoginModel = new LoginModelImpl();
    }

    /**
     * 登录
     */

    public void login(String url, Map<String, String> map) {
        getView().onstart();

        mLoginModel.login(url, map, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().loginSuccess();
            }

            @Override
            public void onError(String msg) {
                getView().loginError(msg);
            }


        });
    }

}
