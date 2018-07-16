package com.pft.quicktouch.mvp.model;

import android.text.TextUtils;

import com.pft.quicktouch.http.CallBackUtil;
import com.pft.quicktouch.http.OkhttpUtil;
import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.GetCodeContract;
import com.pft.quicktouch.tool.NetTool;

import okhttp3.Call;

public class GetCodeModelImpl implements GetCodeContract.GetCodeModel {
    @Override
    public void getCode(String url, String phone, final ResultCallBack callBack) {
        if (TextUtils.isEmpty(phone)) {
            callBack.onError("手机号错误");

        } else {

            try {
                OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        callBack.onError(e.toString());
                    }

                    @Override
                    public void onResponse(String response) {

                    }
                });
            } catch (Exception c) {
                callBack.onError(c.toString());
            }
        }
    }

    @Override
    public void vififyCode(String url, String phone, String code, final ResultCallBack callBack) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
            callBack.onError("请填写验证码");

        } else {
            try {
                OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        callBack.onError(e.toString());
                    }

                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(response);
                    }
                });

            } catch (Exception e) {
                callBack.onError(e.toString());
            }

        }
    }
}
