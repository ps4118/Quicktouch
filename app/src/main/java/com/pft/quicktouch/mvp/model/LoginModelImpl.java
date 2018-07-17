package com.pft.quicktouch.mvp.model;

import com.pft.quicktouch.http.CallBackUtil;
import com.pft.quicktouch.http.OkhttpUtil;
import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.LoginContract;

import java.util.Map;

import okhttp3.Call;

public class LoginModelImpl implements LoginContract.LoginModel {
    @Override
    public void login(String url, Map<String, String> map, final ResultCallBack callBack) {
        //开始登录请求
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
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