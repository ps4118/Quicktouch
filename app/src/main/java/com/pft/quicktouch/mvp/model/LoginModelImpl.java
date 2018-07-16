package com.pft.quicktouch.mvp.model;

import com.pft.quicktouch.http.CallBackUtil;
import com.pft.quicktouch.http.OkhttpUtil;
import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.LoginContract;

import okhttp3.Call;

public class LoginModelImpl implements LoginContract.LoginModel {
    @Override
    public void login(String url, String phone, String pass, final ResultCallBack callBack) {
        //进行字符串的非空判断
        if (phone.equals("") || pass.equals("")) {
            callBack.onError("账号和密码不能为空");
        } else {
            //开始登录请求

            OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {
                    callBack.onError(e.toString());
                }

                @Override
                public void onResponse(String response) {
                    //根据返回状态码返回请求成功还是请求失败

                    callBack.onSuccess(response);
                }
            });
        }
    }
}